package com.example.meditationcomposeapp.data_source.mappers.profile

import com.example.meditationcomposeapp.data_source.entity.UpdateDescriptionResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.login_flow.UpdateDescriptionModel
import javax.inject.Inject

class UpdateDescriptionMapper @Inject constructor() :
    Mapper<UpdateDescriptionModel, UpdateDescriptionResponse> {
    override fun mapFrom(objectFrom: UpdateDescriptionResponse): UpdateDescriptionModel {
        return UpdateDescriptionModel(
            versionName = objectFrom.versionName,
            updateReleaseTime = objectFrom.updateReleaseTime,
            updateTitle = objectFrom.updateTitle,
            updateDescription = objectFrom.updateDescription,
            wasShown = false
        )
    }
}