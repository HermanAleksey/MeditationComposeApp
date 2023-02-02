package com.example.core.updates_history

import com.example.common.mapper.Mapper
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.meditationcomposeapp.data_source.entity.network.UpdateDescriptionResponse
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