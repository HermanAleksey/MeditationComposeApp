package com.justparokq.feature.chat.api.presentation

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justparokq.core.common.mvi.MviViewModel
import com.justparokq.core.common.utils.emptyString
import com.justparokq.feature.chat.api.data.mapper.ChatWSDataParser
import com.justparokq.feature.chat.api.data.web_socket.ChatWebSocketListener
import com.justparokq.feature.chat.api.data.web_socket.WebSocketConnectionStatus
import com.justparokq.feature.chat.api.data.web_socket.WebSocketInteractor
import com.justparokq.feature.chat.api.model.ChatWSData
import com.justparokq.feature.chat.internal.presentation.model.OtherUserMessageUIModel
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
    private val wsDataParser: ChatWSDataParser,
) : MviViewModel<ChatScreenState, ChatScreenAction>, ViewModel() {

    private var webSocket: WebSocket? = null

    private val _uiState = MutableStateFlow<ChatScreenState>(ChatScreenState.Loading)

    override val uiState: StateFlow<ChatScreenState>
        get() = _uiState.asStateFlow()

    init {
        connectAndObserveWebSocket()
    }

    override fun processAction(action: ChatScreenAction) {
        when (action) {
            is ChatScreenAction.OnTextInputChanged -> updateInputText(action.text)
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
                        _uiState.update {
                            ChatScreenState.Success(
                                inputMessage = emptyString(),
                                messagesList = listOf()
                            )
                        }
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
                        val wsData = wsDataParser.mapFrom(message)

                        //todo move
                        when (wsData) {
                            is ChatWSData.Message -> OtherUserMessageUIModel(
                                text = wsData.text,
                                time = wsData.time,
                                userName = wsData.userName
                            )
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                }
                .collectLatest { uiMessage ->
                    // todo each time recreate uiState? really? refactor it after testing
                    if (uiMessage == null) return@collectLatest

                    (_uiState.value as? ChatScreenState.Success)?.let { currentState ->
                        _uiState.update {
                            currentState.copy(
                                messagesList = currentState.messagesList + uiMessage
                            )
                        }
                    }
                }
        }
    }

    private fun updateInputText(text: String) {
        (_uiState.value as? ChatScreenState.Success)?.let { previousState ->
            _uiState.update {
                previousState.copy(
                    inputMessage = text
                )
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun sendMessage() {
        (_uiState.value as? ChatScreenState.Success)?.let { currentState ->
            val text = currentState.inputMessage

            val sdf = SimpleDateFormat("HH:mm")
            val date = Date()
            val currentTime = sdf.format(date)

            val message = ChatWSData.Message(
                text = text,
                userName = "O",
                time = currentTime
            )
            val encodedMessage = wsDataParser.mapTo(message)
            Log.e("TAGG", "sendMessage: $encodedMessage")
            webSocket?.send(encodedMessage)
            updateInputText(emptyString())
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