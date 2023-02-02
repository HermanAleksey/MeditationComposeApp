package com.example.core.updates_history.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.core.updates_history.model.UpdateDescriptionResponse
import javax.inject.Inject

class UpdateDescriptionResponseMapper @Inject constructor() :
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