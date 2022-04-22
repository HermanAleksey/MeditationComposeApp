package com.example.meditationcomposeapp.model.entity

import com.google.gson.annotations.SerializedName

data class SuccessInfo(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String?
)
