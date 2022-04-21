package com.example.meditationcomposeapp.data_source.entity

import com.example.meditationcomposeapp.model.entity.Profile
import com.google.gson.annotations.SerializedName
import java.util.*

data class LoginUserResponse(
    @SerializedName("name")
    val userName: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("place")
    val placeOfResidence: String,
    @SerializedName("sleep_statistic")
    val sleepStatistic: List<SleepDetailsResponse>
) {
    data class SleepDetailsResponse(
        @SerializedName("date")
        val date: Date,
        @SerializedName("sleep_time")
        val sleepTime: Int,
        @SerializedName("deep")
        val deepSleepTime: Int,
        @SerializedName("fast")
        val fastSleepTime: Int,
        @SerializedName("light")
        val lightSleepTime: Int,
        @SerializedName("awaken")
        val numberOfAwakenings: Int,
    )
}