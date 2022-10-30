package com.example.meditationcomposeapp.presentation.utils

import com.example.meditationcomposeapp.model.entity.updates_log.UpdateDescriptionModel

fun getVersionDescriptions(): List<UpdateDescriptionModel> {
    val version0_0_1 = UpdateDescriptionModel(
        versionName = "0.0.1",
        updateReleaseTime = 1667034395445,
        updateTitle = "Project initialization!",
        updateDescription = "Project was created. This update created in order to show origin.",
        wasShown = false
    )
    val version0_5_0 = UpdateDescriptionModel(
        versionName = "0.5.0",
        updateReleaseTime = 1667034395445,
        updateTitle = "A lot of Beer!",
        updateDescription = "Added new beer api with more details. Also detailed screen were added for" +
                "each beer! Just click on it. Button on items of the list were meant to navigate to" +
                "beer page on an online store or similar.",
        wasShown = false
    )
    val version0_6_0 = UpdateDescriptionModel(
        versionName = "0.6.0",
        updateReleaseTime = 1667138968792,
        updateTitle = "Update notes!",
        updateDescription = "Now you can see what is new in the app with less effort! Also you can check updates history.",
        wasShown = false
    )
    val version0_6_1 = UpdateDescriptionModel(
        versionName = "0.6.1",
        updateReleaseTime = 1667152000868,
        updateTitle = "Bug fix",
        updateDescription = "Bug with infinite update description pop-up fixed :)",
        wasShown = false
    )

    return listOf(
        version0_0_1,
        version0_5_0,
        version0_6_0,
        version0_6_1
    )
}