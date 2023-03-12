package com.example.core.updates_history.use_case

import com.example.core.model.updates.UpdateDescriptionModel
import com.example.core.updates_history.repository.db.UpdateDescriptionDBRepository
import javax.inject.Inject

interface InsertAllUpdatesDescriptionsUseCase {

    suspend operator fun invoke(updates: List<UpdateDescriptionModel>)
}

class InsertAllUpdatesDescriptionsUseCaseImpl @Inject constructor(
    private val repository: UpdateDescriptionDBRepository
) : InsertAllUpdatesDescriptionsUseCase {

    override suspend fun invoke(updates: List<UpdateDescriptionModel>) {
        updates.forEach {
            repository.insertAll(it)
        }
    }
}