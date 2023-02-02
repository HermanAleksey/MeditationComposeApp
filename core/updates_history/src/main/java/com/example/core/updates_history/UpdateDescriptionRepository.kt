package com.example.core.updates_history

import com.example.core.model.updates.UpdateDescriptionModel

interface UpdateDescriptionRepository {

    suspend fun getAll(): List<UpdateDescriptionModel>

    suspend fun getLastUpdate(): UpdateDescriptionModel?

    suspend fun insertAll(vararg updates: UpdateDescriptionModel)

    suspend fun delete(update: UpdateDescriptionModel)
}