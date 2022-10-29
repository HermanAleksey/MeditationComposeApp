package com.example.meditationcomposeapp.data_source.repository.update_description

import com.example.meditationcomposeapp.data_source.database.dao.UpdateDescriptionDao
import com.example.meditationcomposeapp.data_source.entity.toDbEntity
import com.example.meditationcomposeapp.data_source.entity.toUiModel
import com.example.meditationcomposeapp.model.entity.updates_log.UpdateDescriptionModel
import javax.inject.Inject

class UpdateDescriptionRepositoryImpl @Inject constructor(
    private val dao: UpdateDescriptionDao,
) : UpdateDescriptionRepository {
    override suspend fun getAll(): List<UpdateDescriptionModel> {
        return dao.getAll().map {
            dao.setIsShown(it.id, true)
            it.toUiModel()
        }
    }

    override suspend fun getLastUpdate(): UpdateDescriptionModel? {
//        return dao.getLastUpdate()?.toUiModel()
        //todo remove test hard-code
        return  UpdateDescriptionModel("10.10.10", 1667045395445, "title", "desc", false)
    }

    override suspend fun insertAll(vararg updates: UpdateDescriptionModel) {
        dao.insertAll(*updates.map { it.toDbEntity() }.toTypedArray())
    }

    override suspend fun delete(update: UpdateDescriptionModel) {
        dao.delete(update.toDbEntity())
    }
}