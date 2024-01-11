package com.justparokq.feature.chat.internal.model

interface MessageUiModel

data class OtherUserMessageUIModel(
    val text: String,
    val time: String,
    val userName: String,
) : MessageUiModel

data class CurrentUserMessageUIModel(
    val text: String,
    val time: String,
    val isSent: Boolean,
) : MessageUiModel