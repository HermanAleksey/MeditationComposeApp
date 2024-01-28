package com.justparokq.feature.chat.api.presentation

import com.justparokq.core.common.mvi.MviState
import com.justparokq.feature.chat.internal.presentation.model.MessageUiModel
import java.security.PrivateKey
import java.security.PublicKey

sealed interface ChatScreenState : MviState {

    data class Success(
        val serverPublicKey: PublicKey?,
        val clientPublicKey: PublicKey?,
        val clientPrivateKey: PrivateKey?,
        val userName: String,
        val inputMessage: String,
        val messagesList: List<MessageUiModel>,
    ) : ChatScreenState

    data class Error(
        val errorText: String,
    ) : ChatScreenState

    object Loading : ChatScreenState
}