package com.justparokq.feature.chat.api.presentation

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justparokq.core.common.mvi.MviViewModel
import com.justparokq.feature.chat.api.data.mapper.ChatWsDataDtoMapper
import com.justparokq.feature.chat.api.data.mapper.ChatWsDataDtoMapperFactory
import com.justparokq.feature.chat.api.data.mapper.JsonChatWSDtoMapper
import com.justparokq.feature.chat.api.data.mapper.MessageEncrypt
import com.justparokq.feature.chat.api.data.mapper.MessageUiModelMapper
import com.justparokq.feature.chat.api.data.mapper.PublicKeyStringEncrypt
import com.justparokq.feature.chat.api.data.provider.KeyPairFactory
import com.justparokq.feature.chat.api.data.web_socket.ChatWebSocketListener
import com.justparokq.feature.chat.api.data.web_socket.WebSocketConnectionStatus
import com.justparokq.feature.chat.api.data.web_socket.WebSocketInteractor
import com.justparokq.feature.chat.api.model.ChatWSData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.WebSocket
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val chatWSListener: ChatWebSocketListener,
    private val wsInteractor: WebSocketInteractor,
    private val chatWsDataDtoMapperFactory: ChatWsDataDtoMapperFactory,
    private val jsonChatWSDtoMapper: JsonChatWSDtoMapper,
    private val keyPairFactory: KeyPairFactory,
    private val messageUiModelMapper: MessageUiModelMapper,
    private val publicKeyStringEncrypt: PublicKeyStringEncrypt,
    private val messageEncrypt: MessageEncrypt,
) : MviViewModel<ChatScreenState, ChatScreenAction>, ViewModel() {

    private lateinit var chatWsDataDtoMapper: ChatWsDataDtoMapper
    private var webSocket: WebSocket? = null
    private val _uiState = MutableStateFlow<ChatScreenState>(ChatScreenState.Loading)
    private var stateManager = StateManager(_uiState)

    override val uiState: StateFlow<ChatScreenState>
        get() = _uiState.asStateFlow()

    init {
        createAndSetClientKeyPair()
        connectAndObserveWebSocket()
    }

    private fun createAndSetClientKeyPair() {
        val (publicKey, privateKey) = keyPairFactory.generateKeyPair()
        stateManager.updateKeys(publicKey, privateKey)
    }


    @SuppressLint("SimpleDateFormat")
    override fun processAction(action: ChatScreenAction) {
        when (action) {
            is ChatScreenAction.OnTextInputChanged -> stateManager.updateInputText(action.text)
            ChatScreenAction.OnSendMessageClicked -> {
                val inputTextMessage = stateManager.getInputText() ?: return
                val userName = stateManager.getCurrentUserName() ?: return

                val currentTime = SimpleDateFormat("HH:mm").format(Date())
                val wsData = ChatWSData.Message(
                    text = inputTextMessage,
                    userName = userName,
                    time = currentTime,
                )
                sendWSMessage(wsData)
                stateManager.clearInputText()
            }

            ChatScreenAction.OnTryReconnectButtonClicked -> connectAndObserveWebSocket()
        }
    }

    private fun connectAndObserveWebSocket() {
        viewModelScope.launch {
            webSocket = wsInteractor.createWebSocket(chatWSListener)
            observeWebSocketConnection()
            observeWebSocketMessages()
        }
    }

    private fun observeWebSocketConnection() {
        viewModelScope.launch(Dispatchers.Default) {
            chatWSListener.isWebSocketConnected.collectLatest { status ->
                when (status) {
                    WebSocketConnectionStatus.Connected -> {
                        val publicClientKey = stateManager.getClientPublicKey()
                        val privateClientKey = stateManager.getClientPrivateKey()
                        val publicServerKeyString = stateManager.getServerPublicKey()
                        if (
                            privateClientKey == null
                            || publicServerKeyString == null
                            || publicClientKey == null
                        ) return@collectLatest

                        val publicServerKey = publicKeyStringEncrypt.decryptPublicKey(
                            publicServerKeyString
                        )

                        chatWsDataDtoMapper = chatWsDataDtoMapperFactory.create(
                            privateClientKey = privateClientKey,
                            publicServerKey = publicServerKey,
                        )
                        //todo может быть проблема. Мы коннектимся и тут мы ещё не может надеясться на пбличный ключ сервера. Переделаь
                        val clientPublicKeyWSData = ChatWSData.PublicKey(publicClientKey)
                        val dto = chatWsDataDtoMapper.mapFrom(clientPublicKeyWSData)
                        val message = jsonChatWSDtoMapper.mapFrom(dto)
                        webSocket?.send(message)

                        stateManager.setEmptySuccessState()
                    }

                    is WebSocketConnectionStatus.Disconnected -> {
                        _uiState.update {
                            ChatScreenState.Error(status.reason)
                        }
                    }

                    is WebSocketConnectionStatus.Disconnecting -> {
                        _uiState.update {
                            ChatScreenState.Loading
                        }
                    }

                    is WebSocketConnectionStatus.Error -> {
                        _uiState.update {
                            ChatScreenState.Error(status.exception.toString())
                        }
                    }

                    WebSocketConnectionStatus.Idle -> {
                        _uiState.update {
                            ChatScreenState.Loading
                        }
                    }
                }
            }
        }
    }

    private fun observeWebSocketMessages() {
        viewModelScope.launch(Dispatchers.Default) {
            chatWSListener.messagesFlow
                .map { message ->
                    if (message.isEmpty()) return@map null

                    try {
                        val dto = jsonChatWSDtoMapper.mapTo(message)
                        val wsData = chatWsDataDtoMapper.mapTo(dto)
                        wsData
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                }
                .collectLatest { chatWsData ->
                    Log.e("TAGG", "observeWebSocketMessages: uiMessage")
                    chatWsData ?: return@collectLatest

                    processWSChatData(chatWsData)
                }
        }
    }

    private fun processWSChatData(chatWsData: ChatWSData) {
        when (chatWsData) {
            is ChatWSData.Message -> {
                val currentUserName = stateManager.getCurrentUserName() ?: return
                val uiMessage = messageUiModelMapper.mapFrom(
                    message = chatWsData,
                    currentUserName = currentUserName
                )
                stateManager.addMessageToChat(uiMessage)
            }

            is ChatWSData.Connected -> {
                stateManager.updateUserName(chatWsData.userName)
            }

            is ChatWSData.PublicKey -> {
                stateManager.updateServerPublicKey(chatWsData.publicKey)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun sendWSMessage(wsData: ChatWSData) {
        webSocket?.let { webSocket ->
            val dto = chatWsDataDtoMapper.mapFrom(wsData)
            val message = jsonChatWSDtoMapper.mapFrom(dto)
            webSocket.send(message)
        }
    }

    override fun onCleared() {
        webSocket?.let {
            wsInteractor.disconnectFromWebSocket(it)
        }
        wsInteractor.shutDown()
        super.onCleared()
    }
}