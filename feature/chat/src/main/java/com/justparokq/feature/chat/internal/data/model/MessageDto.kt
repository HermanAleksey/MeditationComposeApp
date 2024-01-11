package com.justparokq.feature.chat.internal.data.model

// Send message to server chat
class MessageDto(
    val messageId: Int,
    val userName: String,
    val text: String,
)