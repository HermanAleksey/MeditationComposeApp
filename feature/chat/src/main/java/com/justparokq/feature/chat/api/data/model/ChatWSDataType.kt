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

    @SerializedName("message")
    MESSAGE("message", MessageDataDto::class.java);

    companion object {

        fun parseFromString(type: String): ChatWSDataType? {
            return values().find { it.serverKey == type }
        }
    }
}