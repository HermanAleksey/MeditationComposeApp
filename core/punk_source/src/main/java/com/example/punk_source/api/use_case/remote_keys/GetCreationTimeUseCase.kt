package com.example.punk_source.api.use_case.remote_keys

import com.example.punk_source.api.repository.RemoteKeysRepository
import javax.inject.Inject

interface GetCreationTimeUseCase {

    suspend operator fun invoke(): Long?
}

class GetCreationTimeUseCaseImpl @Inject constructor(
    private val repository: RemoteKeysRepository
) : GetCreationTimeUseCase {

    override suspend fun invoke(): Long? = repository.getCreationTime()
}