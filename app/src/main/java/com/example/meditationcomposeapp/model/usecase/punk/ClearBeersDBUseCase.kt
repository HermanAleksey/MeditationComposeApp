package com.example.meditationcomposeapp.model.usecase.punk

import com.example.meditationcomposeapp.data_source.repository.punk.PunkDBRepository
import javax.inject.Inject

interface ClearBeersDBUseCase {

    suspend operator fun invoke()
}

class ClearBeersDBUseCaseImpl @Inject constructor(
    private val repository: PunkDBRepository
) : ClearBeersDBUseCase {


    override suspend fun invoke() =
        repository.clear()
}

