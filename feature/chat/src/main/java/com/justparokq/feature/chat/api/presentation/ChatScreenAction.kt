package com.justparokq.feature.chat.api.presentation

import com.justparokq.core.common.mvi.MviAction

sealed interface ChatScreenAction : MviAction {

    object OnSendMessageClicked : ChatScreenAction

    class OnTextInputChanged(
        val text: String,
    ) : ChatScreenAction

    object OnTryReconnectButtonClicked : ChatScreenAction
}
