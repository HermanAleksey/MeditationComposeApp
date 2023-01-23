package com.example.meditationcomposeapp.model.usecase.remote_keys

import com.example.meditationcomposeapp.data_source.repository.punk.RemoteKeysRepository
import javax.inject.Inject

interface GetCreationTimeUseCase {

    suspend operator fun invoke(): Long?
}

class GetCreationTimeUseCaseImpl @Inject constructor(
    private val repository: RemoteKeysRepository
) : GetCreationTimeUseCase {

    override suspend fun invoke(): Long? = repository.getCreationTime()
}