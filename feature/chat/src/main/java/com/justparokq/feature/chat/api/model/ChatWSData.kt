package com.justparokq.feature.chat.api.model

sealed interface ChatWSData {

    data class PublicKey(
        val publicKey: java.security.PublicKey,
        val userName: String,
    ) : ChatWSData

    data class Message(
        val text: String,
        val userName: String,
        val time: String,
    ) : ChatWSData

    data class Connected(
        val userName: String,
    ) : ChatWSData
}