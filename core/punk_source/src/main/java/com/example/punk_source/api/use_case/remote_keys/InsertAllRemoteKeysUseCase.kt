package com.example.punk_source.api.use_case.remote_keys

import com.example.database.model.RemoteKeys
import com.example.punk_source.api.repository.RemoteKeysRepository
import javax.inject.Inject

interface InsertAllRemoteKeysUseCase {

    suspend operator fun invoke(remoteKey: List<RemoteKeys>)
}

class InsertAllRemoteKeysUseCaseImpl @Inject constructor(
    private val repository: RemoteKeysRepository
) : InsertAllRemoteKeysUseCase {

    override suspend fun invoke(remoteKey: List<RemoteKeys>) =
        repository.insertAll(remoteKey)
}