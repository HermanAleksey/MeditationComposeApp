package com.example.core.updates_history.use_case

import com.example.core.model.updates.UpdateDescriptionModel
import com.example.core.updates_history.repository.db.UpdateDescriptionDBRepository
import javax.inject.Inject

interface GetAllUpdatesDescriptionsUseCase {

    suspend operator fun invoke(): List<UpdateDescriptionModel>
}

class GetAllUpdatesDescriptionsUseCaseImpl @Inject constructor(
    private val repository: UpdateDescriptionDBRepository
): GetAllUpdatesDescriptionsUseCase {

    override suspend fun invoke(): List<UpdateDescriptionModel> {
        return repository.getAll().reversed()
    }
}