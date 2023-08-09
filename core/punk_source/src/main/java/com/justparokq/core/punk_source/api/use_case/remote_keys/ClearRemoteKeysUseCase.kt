package com.justparokq.core.punk_source.api.use_case.remote_keys

import com.justparokq.core.punk_source.api.repository.RemoteKeysRepository
import javax.inject.Inject

interface ClearRemoteKeysUseCase {
    suspend operator fun invoke()
}

class ClearRemoteKeysUseCaseImpl @Inject constructor(
    private val repository: RemoteKeysRepository
) : ClearRemoteKeysUseCase {

    override suspend fun invoke() = repository.clearRemoteKeys()
}