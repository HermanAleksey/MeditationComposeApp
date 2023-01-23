package com.example.meditationcomposeapp.data_source.entity.network

import com.google.gson.annotations.SerializedName

data class UpdateDescriptionResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val versionName: String,
    @SerializedName("release_time") val updateReleaseTime: Long,
    @SerializedName("title") val updateTitle: String,
    @SerializedName("description") val updateDescription: String,
)