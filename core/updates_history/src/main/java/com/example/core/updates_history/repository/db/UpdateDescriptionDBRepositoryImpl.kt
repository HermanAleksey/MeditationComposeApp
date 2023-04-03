package com.example.core.updates_history.repository.db

import com.example.common.mapper.BidirectionalMapper
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.database.dao.UpdateDescriptionDao
import com.example.database.model.UpdateDescriptionDBEntity
import javax.inject.Inject

class UpdateDescriptionDBRepositoryImpl @Inject constructor(
    private val dao: UpdateDescriptionDao,
    private val mapper: BidirectionalMapper<UpdateDescriptionModel, UpdateDescriptionDBEntity>,
) : UpdateDescriptionDBRepository {
    override suspend fun getAll(): List<UpdateDescriptionModel> {
        return dao.getAll().map { update ->
            if (!update.isShown)
                setIsShown(update)

            mapper.mapFrom(update)
        }
    }

    override suspend fun getLastUpdate(): UpdateDescriptionModel? {
        val lastUpdate = dao.getLastUpdate()
        if (lastUpdate?.isShown == false)
            setIsShown(lastUpdate)

        return lastUpdate?.let { mapper.mapFrom(lastUpdate) }
    }

    override suspend fun insertAll(vararg updates: UpdateDescriptionModel) {
        dao.insertAll(*updates.map { mapper.mapTo(it) }.toTypedArray())
    }

    override suspend fun delete(update: UpdateDescriptionModel) {
        dao.delete(mapper.mapTo(update))
    }

    private suspend fun setIsShown(update: UpdateDescriptionDBEntity) {
        dao.setIsShown(
            versionName = update.versionName,
            wasShown = true
        )
    }
}