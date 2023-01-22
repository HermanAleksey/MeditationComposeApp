package com.example.meditationcomposeapp.data_source.repository.punk

import com.example.meditationcomposeapp.data_source.database.dao.RemoteKeysDao
import com.example.meditationcomposeapp.data_source.entity.db.RemoteKeys
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