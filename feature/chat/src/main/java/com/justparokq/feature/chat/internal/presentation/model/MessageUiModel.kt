package com.justparokq.feature.chat.internal.presentation.model

import androidx.compose.runtime.Stable

interface MessageUiModel

@Stable
data class OtherUserMessageUIModel(
    val text: String,
    val time: String,
    val userName: String,
) : MessageUiModel

@Stable
data class CurrentUserMessageUIModel(
    val text: String,
    val time: String,
    val isSent: Boolean,
) : MessageUiModel