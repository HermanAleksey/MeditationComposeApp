package com.justparokq.feature.chat.api.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PublicKeyDataDto(
    @Expose
    @SerializedName("dto_type")
    override val type: ChatWSDataType,
    @Expose
    @SerializedName("public_key")
    val publicKey: String,
) : ChatWSDataDto(type)