package com.justparokq.feature.chat.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justparokq.core.common.mvi.MviViewModel
import com.justparokq.feature.chat.internal.data.web_socket.ChatWebSocketListener
import com.justparokq.feature.chat.internal.data.web_socket.WebSocketConnectionStatus
import com.justparokq.feature.chat.internal.data.web_socket.WebSocketInteractor
import com.justparokq.feature.chat.internal.presentation.model.CurrentUserMessageUIModel
import com.justparokq.feature.chat.internal.presentation.model.OtherUserMessageUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.WebSocket
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val chatWSListener: ChatWebSocketListener,
    private val wsInteractor: WebSocketInteractor,
) : MviViewModel<ChatScreenState, ChatScreenAction>, ViewModel() {

    private val webSocket: WebSocket? = null

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
            wsInteractor.createWebSocket(chatWSListener)
            chatWSListener.isWebSocketConnected.collectLatest { status ->
                when (status) {
                    WebSocketConnectionStatus.Connected -> {
                        _uiState.update {
                            ChatScreenState.Success(
                                inputMessage = "",
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
            chatWSListener.messagesFlow
                .map { message -> //todo move mappeing to smwhere else
                    val firstCharEven = (message.first().digitToInt()) % 2 == 0
                    if (firstCharEven)
                        OtherUserMessageUIModel(
                            text = message,
                            time = "13:00",
                            userName = "O"
                        )
                    else
                        CurrentUserMessageUIModel(
                            text = message,
                            time = "13:01",
                            isSent = true
                        )
                }
                .collectLatest { message ->
                    // todo each time recreate uiState? really? refactor it after testing
                    (_uiState.value as? ChatScreenState.Success)?.let { currentState ->
                        _uiState.update {
                            currentState.copy(
                                messagesList = currentState.messagesList + message
                            )
                        }
                    }
                }


            // todo test initial data . remove
            _uiState.update {
                ChatScreenState.Success(
                    inputMessage = "",
                    messagesList = listOf(
                        OtherUserMessageUIModel(
                            text = "Hello",
                            time = "13:00",
                            userName = "P"
                        ),
                        CurrentUserMessageUIModel(
                            text = "HI!",
                            time = "13:01",
                            isSent = true
                        )
                    )
                )
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

    private fun sendMessage() {
        (_uiState.value as? ChatScreenState.Success)?.let { currentState ->
            webSocket?.send(currentState.inputMessage)
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