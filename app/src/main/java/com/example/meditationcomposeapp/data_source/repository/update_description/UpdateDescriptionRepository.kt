package com.example.meditationcomposeapp.data_source.repository.update_description

import com.example.meditationcomposeapp.model.entity.updates_log.UpdateDescriptionModel

interface UpdateDescriptionRepository {

    suspend fun getAll(): List<UpdateDescriptionModel>

    suspend fun getLastUpdate(): UpdateDescriptionModel

    suspend fun insertAll(vararg updates: UpdateDescriptionModel)

    suspend fun delete(update: UpdateDescriptionModel)
}