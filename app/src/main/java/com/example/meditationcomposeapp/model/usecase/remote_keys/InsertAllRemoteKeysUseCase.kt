package com.example.meditationcomposeapp.model.usecase.remote_keys

import com.example.meditationcomposeapp.data_source.entity.db.RemoteKeys
import com.example.meditationcomposeapp.data_source.repository.punk.RemoteKeysRepository
import javax.inject.Inject

interface InsertAllRemoteKeysUseCase {

    suspend operator fun invoke(remoteKey: List<RemoteKeys>)
}

class InsertAllRemoteKeysUseCaseImpl @Inject constructor(
    private val repository: RemoteKeysRepository
) : InsertAllRemoteKeysUseCase {

    override suspend fun invoke(remoteKey: List<RemoteKeys>) =
        repository.clearRemoteKeys()
}