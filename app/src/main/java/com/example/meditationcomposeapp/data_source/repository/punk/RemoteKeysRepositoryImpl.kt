package com.example.meditationcomposeapp.data_source.repository.punk

import com.example.meditationcomposeapp.data_source.database.dao.RemoteKeysDao
import com.example.meditationcomposeapp.data_source.entity.db.RemoteKeys
import javax.inject.Inject

class RemoteKeysRepositoryImpl @Inject constructor(
    private val remoteKeysDao: RemoteKeysDao
) {

    suspend fun insertAll(remoteKeys: List<RemoteKeys>) = remoteKeysDao.insertAll(remoteKeys)

    suspend fun getRemoteKeyByBeerID(id: Int) = remoteKeysDao.getRemoteKeyByBeerID(id)

    suspend fun clearRemoteKeys() = remoteKeysDao.clearRemoteKeys()

    suspend fun getCreationTime() = remoteKeysDao.getCreationTime()

}