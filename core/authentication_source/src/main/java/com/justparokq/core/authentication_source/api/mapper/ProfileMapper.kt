package com.justparokq.core.authentication_source.api.mapper

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.authentication_source.api.model.LoginUserResponse
import com.justparokq.core.model.authentication.Profile
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