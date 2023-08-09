package com.justparokq.core.punk_source.api.repository

import com.justparokq.core.punk_source.api.repository.RemoteKeysRepository
import com.justparokq.core.database.dao.RemoteKeysDao
import com.justparokq.core.database.model.RemoteKeys
import javax.inject.Inject

class RemoteKeysRepositoryImpl @Inject constructor(
    private val remoteKeysDao: RemoteKeysDao
) : RemoteKeysRepository {

    override suspend fun insertAll(remoteKeys: List<RemoteKeys>) =
        remoteKeysDao.insertAll(remoteKeys)

    override suspend fun getRemoteKeyByBeerID(id: Int) = remoteKeysDao.getRemoteKeyByBeerID(id)

    override suspend fun clearRemoteKeys() = remoteKeysDao.clearRemoteKeys()

    override suspend fun getCreationTime() = remoteKeysDao.getCreationTime()

}