package com.justparokq.feature.chat.api

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

) : MviViewModel<ChatScreenState, ChatScreenAction> {

    private val _uiState = MutableStateFlow(
        //test data
        ChatScreenState(
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
    )

    init {

    }

    override val uiState: StateFlow<ChatScreenState>
        get() = _uiState.asStateFlow()

    override fun processAction(action: ChatScreenAction) {
        when (action) {
            is ChatScreenAction.OnTextInputChanged -> updateInputText(action.text)
            is ChatScreenAction.OnSendMessageClicked -> sendMessage()
        }
    }

    private fun updateInputText(text: String) {
        _uiState.update {
            it.copy(
                inputMessage = text
            )
        }
    }

    private fun sendMessage() {
        //todo send
    }
}