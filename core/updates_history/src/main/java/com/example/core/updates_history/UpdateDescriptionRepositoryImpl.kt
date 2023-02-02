package com.example.core.updates_history

import com.example.common.mapper.Mapper
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.database.dao.UpdateDescriptionDao
import com.example.database.model.UpdateDescriptionDBEntity
import javax.inject.Inject

class UpdateDescriptionRepositoryImpl @Inject constructor(
    private val dao: UpdateDescriptionDao,
    private val mapper: Mapper<UpdateDescriptionModel, UpdateDescriptionDBEntity>
) : UpdateDescriptionRepository {
    override suspend fun getAll(): List<UpdateDescriptionModel> {
        return dao.getAll().map {
            if (!it.isUpdateWasShown)
                dao.setIsShown(it.versionName, true)
            mapper.mapFrom(it)
        }
    }

    override suspend fun getLastUpdate(): UpdateDescriptionModel? {
        val lastUpdate = dao.getLastUpdate()
        if (lastUpdate?.isUpdateWasShown == false)
            dao.setIsShown(lastUpdate.versionName, true)
        return lastUpdate?.let { mapper.mapFrom(lastUpdate) }
    }

    override suspend fun insertAll(vararg updates: UpdateDescriptionModel) {
        //todo move to Mapper
        dao.insertAll(*updates.map { toDbEntity(it) }.toTypedArray())
    }

    private fun toDbEntity(model: UpdateDescriptionModel): UpdateDescriptionDBEntity =
    UpdateDescriptionDBEntity(
        model.versionName,
        model.updateReleaseTime,
        model.updateTitle,
        model.updateDescription,
    false,
    )

    override suspend fun delete(update: UpdateDescriptionModel) {
        dao.delete(toDbEntity(update))
    }
}