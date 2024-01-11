package com.justparokq.feature.chat.api

import androidx.compose.runtime.Stable
import com.justparokq.core.common.mvi.MviState
import com.justparokq.feature.chat.internal.presentation.model.MessageUiModel

@Stable
data class ChatScreenState(
    val inputMessage: String,
    val messagesList: List<MessageUiModel>,
) : MviState