package com.justparokq.feature.chat.api.data.model

import com.google.gson.annotations.SerializedName

sealed class ChatWSDataDto(

    @Transient
    open val type: ChatWSDataType,
)

enum class ChatWSDataType(
    val serverKey: String = "",
    val drivenClass: Class<out ChatWSDataDto>,
) {

    @SerializedName("public_key")
    PUBLIC_KEY("public_key", PublicKeyDataDto::class.java),

    @SerializedName("message")
    MESSAGE("message", MessageDataDto::class.java),

    @SerializedName("connected")
    CONNECTED("connected", ConnectedDataDto::class.java);

    companion object {

        fun parseFromString(type: String): ChatWSDataType? {
            return values().find { it.serverKey == type }
        }
    }
}