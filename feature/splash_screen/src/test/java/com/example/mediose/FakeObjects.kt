package com.example.mediose

import com.example.core.model.authentication.Profile
import com.example.core.model.updates.UpdateDescriptionModel

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

    fun getFakeUpdateDescriptionModel(
        versionName: String = "0.0.2",
        updateReleaseTime: Long = 1667152000868,
        updateTitle: String = "title",
        updateDescription: String = "update desc",
        wasShown: Boolean = false,
    ) = UpdateDescriptionModel(
        versionName = versionName,
        updateReleaseTime = updateReleaseTime,
        updateTitle = updateTitle,
        updateDescription = updateDescription,
        wasShown = wasShown
    )
}