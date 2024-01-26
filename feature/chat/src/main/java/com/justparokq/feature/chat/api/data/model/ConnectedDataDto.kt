package com.justparokq.feature.chat.api.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ConnectedDataDto(
    @Expose
    @SerializedName("dto_type")
    override val type: ChatWSDataType,
    @Expose
    @SerializedName("user_name")
    val userName: String,
) : ChatWSDataDto(type)