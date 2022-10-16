package com.example.meditationcomposeapp.data_source.mappers.profile

import com.example.meditationcomposeapp.data_source.entity.LoginUserResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.data_source.utils.DateUtils
import com.example.meditationcomposeapp.model.entity.login_flow.Profile
import javax.inject.Inject

class SleepStatisticMapper @Inject constructor(
    private val dateUtils: DateUtils
) : Mapper<Profile.SleepDetails, LoginUserResponse.SleepDetailsResponse> {
    override fun mapFrom(
        objectFrom: LoginUserResponse.SleepDetailsResponse
    ): Profile.SleepDetails {
        return Profile.SleepDetails(
            date = dateUtils.parseStringToDate(
                objectFrom.date,
                datePattern = DateUtils.DatePattern.DATE_INCOME
            ),
            sleepTime = objectFrom.sleepTime,
            deepSleepTime = objectFrom.deepSleepTime,
            fastSleepTime = objectFrom.fastSleepTime,
            lightSleepTime = objectFrom.lightSleepTime,
            numberOfAwakenings = objectFrom.numberOfAwakenings
        )
    }
}