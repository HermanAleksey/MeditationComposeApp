package com.justparokq.feature.chat.internal.presentation.model

import androidx.compose.runtime.Stable

interface MessageUiModel {
    val text: String
    val time: String
}

@Stable
data class OtherUserMessageUIModel(
    override val text: String,
    override val time: String,
    val userName: String,
) : MessageUiModel

@Stable
data class CurrentUserMessageUIModel(
    override val text: String,
    override val time: String,
    val isSent: Boolean,
) : MessageUiModel