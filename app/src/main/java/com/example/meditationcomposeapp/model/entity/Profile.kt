package com.example.meditationcomposeapp.model.entity

import java.time.LocalDate
import java.util.*

data class Profile(
    val userName: String,
    val photo: String,
    val placeOfResidence: String,
    val sleepStatistic: List<SleepDetails>
) {
    data class SleepDetails(
        val date: LocalDate,
        val sleepTime: Int,
        val deepSleepTime: Int,
        val fastSleepTime: Int,
        val lightSleepTime: Int,
        val numberOfAwakenings: Int,
    )
}