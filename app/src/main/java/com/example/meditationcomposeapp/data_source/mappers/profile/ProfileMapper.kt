package com.example.meditationcomposeapp.data_source.mappers.profile

import com.example.meditationcomposeapp.data_source.entity.LoginUserResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.login_flow.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor() : Mapper<Profile, LoginUserResponse> {
    override fun mapFrom(objectFrom: LoginUserResponse): Profile {
        return Profile(
            userName = objectFrom.userName,
            photo = objectFrom.photo,
            placeOfResidence = objectFrom.placeOfResidence,
            otherData = objectFrom.otherData
        )
    }
}