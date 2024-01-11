package com.justparokq.feature.chat.internal.data.model

// object that notifies that message was sent
data class MessageReceivedDto(
    val messageId: Int,
    val userName: String,
)
