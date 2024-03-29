package com.justparokq.core.authentication_source.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class LoginUserResponse(
    @SerializedName("name")
    val userName: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("place")
    val placeOfResidence: String,
    @SerializedName("other_data")
    val otherData: String
)