package com.justparokq.feature.chat.api.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MessageDataDto(
    @Expose
    @SerializedName("type")
    override val type: ChatWSDataType,
    @Expose
    @SerializedName("text")
    val text: String,
    @Expose
    @SerializedName("user_name")
    val userName: String,
    @Expose
    @SerializedName("time")
    val time: String,
) : ChatWSDataDto(type)