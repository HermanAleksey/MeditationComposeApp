package com.justparokq.core.updates_history.repository.db

import com.justparokq.core.model.updates.UpdateDescriptionModel

interface UpdateDescriptionDBRepository {

    suspend fun getAll(): List<UpdateDescriptionModel>

    suspend fun getLastUpdate(): UpdateDescriptionModel?

    suspend fun insertAll(vararg updates: UpdateDescriptionModel)

    suspend fun delete(update: UpdateDescriptionModel)
}