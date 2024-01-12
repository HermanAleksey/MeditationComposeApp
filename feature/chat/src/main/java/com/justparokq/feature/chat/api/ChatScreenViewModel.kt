package com.justparokq.feature.chat.api

import androidx.lifecycle.ViewModel
import com.justparokq.core.common.mvi.MviViewModel
import com.justparokq.feature.chat.internal.presentation.model.CurrentUserMessageUIModel
import com.justparokq.feature.chat.internal.presentation.model.OtherUserMessageUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(

) : MviViewModel<ChatScreenState, ChatScreenAction>, ViewModel() {

    private val _uiState = MutableStateFlow<ChatScreenState>(ChatScreenState.Loading)

    override val uiState: StateFlow<ChatScreenState>
        get() = _uiState.asStateFlow()

    init {
        tryToConnectToChat()
    }

    override fun processAction(action: ChatScreenAction) {
        when (action) {
            is ChatScreenAction.OnTextInputChanged -> updateInputText(action.text)
            ChatScreenAction.OnSendMessageClicked -> sendMessage()
            ChatScreenAction.OnTryReconnectButtonClicked -> tryToConnectToChat()
        }
    }


    private fun tryToConnectToChat() {
        // loading data\connecting
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
        //todo send
    }
}