package com.justparokq.core.updates_history.use_case

import com.justparokq.core.model.updates.UpdateDescriptionModel
import com.justparokq.core.updates_history.repository.db.UpdateDescriptionDBRepository
import javax.inject.Inject

interface GetLastUpdateDescriptionUseCase {

    suspend operator fun invoke(): UpdateDescriptionModel?
}

class GetLastUpdateDescriptionUseCaseImpl @Inject constructor(
    private val repository: UpdateDescriptionDBRepository
): GetLastUpdateDescriptionUseCase {

    override suspend fun invoke(): UpdateDescriptionModel? {
        return repository.getLastUpdate()
    }
}