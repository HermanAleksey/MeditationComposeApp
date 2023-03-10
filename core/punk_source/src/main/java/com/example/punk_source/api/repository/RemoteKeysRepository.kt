package com.example.punk_source.api.repository

import com.example.database.model.RemoteKeys

interface RemoteKeysRepository {

    suspend fun insertAll(remoteKeys: List<RemoteKeys>)

    suspend fun getRemoteKeyByBeerID(id: Int): RemoteKeys?

    suspend fun clearRemoteKeys()

    suspend fun getCreationTime(): Long?
}