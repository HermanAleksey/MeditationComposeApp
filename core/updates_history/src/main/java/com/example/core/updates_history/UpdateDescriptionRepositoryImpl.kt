package com.example.core.updates_history

import com.example.database.dao.UpdateDescriptionDao
import com.example.meditationcomposeapp.data_source.entity.db.toDbEntity
import com.example.meditationcomposeapp.data_source.entity.db.toUiModel
import com.example.meditationcomposeapp.model.entity.login_flow.UpdateDescriptionModel
import javax.inject.Inject

class UpdateDescriptionRepositoryImpl @Inject constructor(
    private val dao: UpdateDescriptionDao,
) : UpdateDescriptionRepository {
    override suspend fun getAll(): List<UpdateDescriptionModel> {
        return dao.getAll().map {
            if (!it.isUpdateWasShown)
                dao.setIsShown(it.versionName, true)
            it.toUiModel()
        }
    }

    override suspend fun getLastUpdate(): UpdateDescriptionModel? {
        val lastUpdate = dao.getLastUpdate()
        if (lastUpdate?.isUpdateWasShown == false)
            dao.setIsShown(lastUpdate.versionName, true)
        return lastUpdate?.toUiModel()
    }

    override suspend fun insertAll(vararg updates: UpdateDescriptionModel) {
        dao.insertAll(*updates.map { it.toDbEntity() }.toTypedArray())
    }

    override suspend fun delete(update: UpdateDescriptionModel) {
        dao.delete(update.toDbEntity())
    }
}