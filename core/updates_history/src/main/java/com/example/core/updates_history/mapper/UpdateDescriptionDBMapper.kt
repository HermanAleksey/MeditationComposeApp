package com.example.core.updates_history.mapper

import com.example.common.mapper.BidirectionalMapper
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.database.model.UpdateDescriptionDBEntity
import javax.inject.Inject

class UpdateDescriptionDBMapper @Inject constructor() :
    BidirectionalMapper<UpdateDescriptionModel, UpdateDescriptionDBEntity> {
    override fun mapFrom(objectFrom: UpdateDescriptionDBEntity): UpdateDescriptionModel {
        return UpdateDescriptionModel(
            versionName = objectFrom.versionName,
            updateReleaseTime = objectFrom.updateReleaseTime,
            updateTitle = objectFrom.updateTitle,
            updateDescription = objectFrom.updateDescription,
            wasShown = objectFrom.isUpdateWasShown
        )
    }

    override fun mapTo(objectFrom: UpdateDescriptionModel): UpdateDescriptionDBEntity {
        return UpdateDescriptionDBEntity(
            objectFrom.versionName,
            objectFrom.updateReleaseTime,
            objectFrom.updateTitle,
            objectFrom.updateDescription,
            objectFrom.wasShown,
        )
    }
}