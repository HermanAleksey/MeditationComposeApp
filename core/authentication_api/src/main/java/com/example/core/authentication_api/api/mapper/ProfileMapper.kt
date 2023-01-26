package com.example.core.authentication_api.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.authentication_api.api.model.LoginUserResponse
import com.example.core.model.authentication.Profile
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