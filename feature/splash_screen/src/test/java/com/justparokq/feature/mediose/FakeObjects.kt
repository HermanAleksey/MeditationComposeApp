package com.justparokq.feature.mediose

import com.justparokq.core.model.authentication.Profile
import com.justparokq.core.model.updates.UpdateDescriptionModel
import com.justparokq.core.model.updates.Version

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
        versionName: Version = Version(0, 0, 2),
        updateReleaseTime: Long = 1667152000868,
        updateTitle: String = "title",
        updateDescription: String = "update desc",
        wasShown: Boolean = false,
    ) = UpdateDescriptionModel(
        version = versionName,
        updateReleaseTime = updateReleaseTime,
        updateTitle = updateTitle,
        updateDescription = updateDescription,
        wasShown = wasShown
    )
}