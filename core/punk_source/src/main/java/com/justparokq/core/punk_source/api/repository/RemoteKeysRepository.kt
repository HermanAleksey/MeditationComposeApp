package com.justparokq.core.punk_source.api.repository

import com.justparokq.core.database.model.RemoteKeys

interface RemoteKeysRepository {

    suspend fun insertAll(remoteKeys: List<RemoteKeys>)

    suspend fun getRemoteKeyByBeerID(id: Int): RemoteKeys?

    suspend fun clearRemoteKeys()

    suspend fun getCreationTime(): Long?
}