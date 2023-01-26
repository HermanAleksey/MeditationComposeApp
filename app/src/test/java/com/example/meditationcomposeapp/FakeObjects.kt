package com.example.meditationcomposeapp

import com.example.meditationcomposeapp.model.entity.beer.*
import com.example.meditationcomposeapp.model.entity.login_flow.UpdateDescriptionModel

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

    fun getFakeBeer(
        id: Int = 1,
        name: String = "name",
        tagline: String = "tagline",
        firstBrewed: String = "10.10.2010",
        description: String = "Veryy good",
        imageUrl: String? = null,
        abv: Double = 10.2,
        ibu: Double? = null,
        targetFg: Int? = null,
        targetOg: Double? = null,
        ebc: Double? = null,
        srm: Double? = null,
        ph: Double? = null,
        attenuationLevel: Double? = null,
        volume: Volume? = null,
        boilVolume: BoilVolume? = null,
        method: Method? = null,
        ingredients: Ingredients? = null,
        foodPairing: List<String> = listOf("Beer"),
        brewersTips: String = "drink",
        contributedBy: String = "Me",
    ) = Beer(
        id = id,
        name = name,
        tagline = tagline,
        firstBrewed = firstBrewed,
        description = description,
        imageUrl = imageUrl,
        abv = abv,
        ibu = ibu,
        targetFg = targetFg,
        targetOg = targetOg,
        ebc = ebc,
        srm = srm,
        ph = ph,
        attenuationLevel = attenuationLevel,
        volume = volume,
        boilVolume = boilVolume,
        method = method,
        ingredients = ingredients,
        foodPairing = foodPairing,
        brewersTips = brewersTips,
        contributedBy = contributedBy,
    )

}