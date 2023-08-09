package com.justparokq.core.updates_history.model

import com.google.gson.annotations.SerializedName

data class UpdateDescriptionResponse(
    @SerializedName("name") val versionName: String,
    @SerializedName("release_time") val updateReleaseTime: Long,
    @SerializedName("title") val updateTitle: String,
    @SerializedName("description") val updateDescription: String,
)