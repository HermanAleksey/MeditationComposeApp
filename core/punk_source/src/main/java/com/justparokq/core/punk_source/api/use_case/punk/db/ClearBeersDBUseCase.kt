package com.justparokq.core.punk_source.api.use_case.punk.db

import com.justparokq.core.punk_source.api.repository.PunkDBRepository
import javax.inject.Inject

interface ClearBeersDBUseCase {

    suspend operator fun invoke()
}

class ClearBeersDBUseCaseImpl @Inject constructor(
    private val repository: PunkDBRepository
) : ClearBeersDBUseCase {

    override suspend fun invoke() = repository.clear()
}

