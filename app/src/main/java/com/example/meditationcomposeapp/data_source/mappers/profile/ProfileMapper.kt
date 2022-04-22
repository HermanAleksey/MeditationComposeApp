package com.example.meditationcomposeapp.data_source.mappers.profile

import com.example.meditationcomposeapp.data_source.entity.LoginUserResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor(
    private val sleepStatisticMapper: Mapper<Profile.SleepDetails, LoginUserResponse.SleepDetailsResponse>
) : Mapper<Profile, LoginUserResponse> {
    override fun mapFrom(objectFrom: LoginUserResponse): Profile {
        return Profile(
            userName = objectFrom.userName,
            photo = objectFrom.photo,
            placeOfResidence = objectFrom.placeOfResidence,
            sleepStatistic = objectFrom.sleepStatistic.map { sleepStatisticMapper.mapFrom(it) }
        )
    }
}