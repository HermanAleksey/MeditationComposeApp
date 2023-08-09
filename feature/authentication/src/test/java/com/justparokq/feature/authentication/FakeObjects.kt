package com.justparokq.feature.authentication

import com.justparokq.core.model.authentication.Profile

object FakeObjects {
    fun getFakeProfile(
        userName: String = "user name",
        photo: String = "photo",
        placeOfResidence: String = "yes",
        otherData: String = "ok",
    ) = Profile(
        userName = userName,
        photo = photo,
        placeOfResidence = placeOfResidence,
        otherData = otherData
    )
}