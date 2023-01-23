package com.example.meditationcomposeapp.model.usecase.remote_keys

import com.example.meditationcomposeapp.data_source.entity.db.RemoteKeys
import com.example.meditationcomposeapp.data_source.repository.punk.RemoteKeysRepository
import javax.inject.Inject

interface GetRemoteKeyBeBeerIdUseCase {

    suspend operator fun invoke(id: Int): RemoteKeys?
}

class GetRemoteKeyBeBeerIdUseCaseImpl @Inject constructor(
    private val repository: RemoteKeysRepository
): GetRemoteKeyBeBeerIdUseCase {

    override suspend fun invoke(id: Int): RemoteKeys? =
        repository.getRemoteKeyByBeerID(id)
}