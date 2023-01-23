package com.example.meditationcomposeapp.data_source.repository.punk

import com.example.meditationcomposeapp.data_source.entity.db.RemoteKeys

interface RemoteKeysRepository {

    suspend fun insertAll(remoteKeys: List<RemoteKeys>)

    suspend fun getRemoteKeyByBeerID(id: Int): RemoteKeys?

    suspend fun clearRemoteKeys()

    suspend fun getCreationTime(): Long?
}