package com.justparokq.core.updates_history.mapper

import com.justparokq.core.common.mapper.BidirectionalMapper
import com.justparokq.core.database.model.UpdateDescriptionDBEntity
import com.justparokq.core.model.updates.UpdateDescriptionModel
import com.justparokq.core.model.updates.toVersion
import javax.inject.Inject

class UpdateDescriptionDBMapper @Inject constructor() :
    BidirectionalMapper<UpdateDescriptionModel, UpdateDescriptionDBEntity> {
    override fun mapFrom(objectFrom: UpdateDescriptionDBEntity): UpdateDescriptionModel {
        return UpdateDescriptionModel(
            version = objectFrom.versionName.toVersion(),
            updateReleaseTime = objectFrom.updateReleaseTime,
            updateTitle = objectFrom.updateTitle,
            updateDescription = objectFrom.updateDescription,
            wasShown = objectFrom.isShown
        )
    }

    override fun mapTo(objectFrom: UpdateDescriptionModel): UpdateDescriptionDBEntity {
        return UpdateDescriptionDBEntity(
            id = 0,
            versionName = objectFrom.version.toString(),
            updateReleaseTime = objectFrom.updateReleaseTime,
            updateTitle = objectFrom.updateTitle,
            updateDescription = objectFrom.updateDescription,
            isShown = objectFrom.wasShown,
        )
    }
}