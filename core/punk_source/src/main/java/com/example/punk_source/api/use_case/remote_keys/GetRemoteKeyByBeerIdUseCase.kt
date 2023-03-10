package com.example.punk_source.api.use_case.remote_keys

import com.example.database.model.RemoteKeys
import com.example.punk_source.api.repository.RemoteKeysRepository
import javax.inject.Inject

interface GetRemoteKeyByBeerIdUseCase {

    suspend operator fun invoke(id: Int): RemoteKeys?
}

class GetRemoteKeyByBeerIdUseCaseImpl @Inject constructor(
    private val repository: RemoteKeysRepository
): GetRemoteKeyByBeerIdUseCase {

    override suspend fun invoke(id: Int): RemoteKeys? =
        repository.getRemoteKeyByBeerID(id)
}