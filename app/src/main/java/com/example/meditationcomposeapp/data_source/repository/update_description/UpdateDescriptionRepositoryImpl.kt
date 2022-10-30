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
            if (!it.isUpdateWasShown)
                dao.setIsShown(it.id, true)
            it.toUiModel()
        }
    }

    override suspend fun getLastUpdate(): UpdateDescriptionModel? {
//        return dao.getLastUpdate()?.toUiModel()
        //todo remove test hard-code
        return UpdateDescriptionModel("1.0.3",
            1667045395445,
            "A lot of beer!",
            "I've added screen with a lot of beer on it. You can click any item and check full info. bla bla bla",
            false)
    }

    override suspend fun insertAll(vararg updates: UpdateDescriptionModel) {
        dao.insertAll(*updates.map { it.toDbEntity() }.toTypedArray())
    }

    override suspend fun delete(update: UpdateDescriptionModel) {
        dao.delete(update.toDbEntity())
    }
}