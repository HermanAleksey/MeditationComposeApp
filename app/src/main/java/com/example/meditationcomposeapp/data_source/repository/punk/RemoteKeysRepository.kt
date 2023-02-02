package com.example.meditationcomposeapp.data_source.repository.punk

interface RemoteKeysRepository {

    suspend fun insertAll(remoteKeys: List<RemoteKeys>)

    suspend fun getRemoteKeyByBeerID(id: Int): RemoteKeys?

    suspend fun clearRemoteKeys()

    suspend fun getCreationTime(): Long?
}