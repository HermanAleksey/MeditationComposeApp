package com.justparokq.feature.chat.api.presentation

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justparokq.core.common.mvi.MviViewModel
import com.justparokq.feature.chat.api.data.mapper.ChatWSDataMapper
import com.justparokq.feature.chat.api.data.mapper.MessageUiModelMapper
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
    private val wsDataParser: ChatWSDataMapper,
    private val messageUiModelMapper: MessageUiModelMapper,
) : MviViewModel<ChatScreenState, ChatScreenAction>, ViewModel() {

    private var webSocket: WebSocket? = null
    private val _uiState = MutableStateFlow<ChatScreenState>(ChatScreenState.Loading)
    private var stateManager = StateManager(_uiState)

    override val uiState: StateFlow<ChatScreenState>
        get() = _uiState.asStateFlow()

    init {
        connectAndObserveWebSocket()
    }

    override fun processAction(action: ChatScreenAction) {
        when (action) {
            is ChatScreenAction.OnTextInputChanged -> stateManager.updateInputText(action.text)
            ChatScreenAction.OnSendMessageClicked -> sendMessage()
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
                        wsDataParser.mapFrom(message)
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
    private fun sendMessage() {
        (_uiState.value as? ChatScreenState.Success)?.let { currentState ->
            val text = currentState.inputMessage
            val currentTime = SimpleDateFormat("HH:mm").format(Date())

            val message = ChatWSData.Message(
                text = text,
                userName = currentState.userName,
                time = currentTime
            )
            val encodedMessage = wsDataParser.mapTo(message)
            Log.e("TAGG", "sendMessage: $encodedMessage")
            webSocket?.send(encodedMessage)
            stateManager.clearInputText()
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