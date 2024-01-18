package com.justparokq.feature.chat.api.model

sealed interface ChatWSData {

    data class Message(
        val text: String,
        val userName: String,
        val time: String,
    ) : ChatWSData

}