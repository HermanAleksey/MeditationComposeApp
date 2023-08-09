package com.example.core.updates_history.mapper

import com.justparokq.core.common.mapper.Mapper
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.core.model.updates.toVersion
import com.example.core.updates_history.model.UpdateDescriptionResponse
import javax.inject.Inject

class UpdateDescriptionResponseMapper @Inject constructor() :
    Mapper<UpdateDescriptionModel, UpdateDescriptionResponse> {
    override fun mapFrom(objectFrom: UpdateDescriptionResponse): UpdateDescriptionModel {
        return UpdateDescriptionModel(
            version = objectFrom.versionName.toVersion(),
            updateReleaseTime = objectFrom.updateReleaseTime,
            updateTitle = objectFrom.updateTitle,
            updateDescription = objectFrom.updateDescription,
            wasShown = false
        )
    }
}