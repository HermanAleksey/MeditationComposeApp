package com.example.meditationcomposeapp.model.usecase.remote_keys

import com.example.meditationcomposeapp.data_source.repository.punk.RemoteKeysRepository
import javax.inject.Inject

interface ClearRemoteKeysUseCase {
    suspend operator fun invoke()
}

class ClearRemoteKeysUseCaseImpl @Inject constructor(
    private val repository: RemoteKeysRepository
) : ClearRemoteKeysUseCase {

    override suspend fun invoke() = repository.clearRemoteKeys()
}