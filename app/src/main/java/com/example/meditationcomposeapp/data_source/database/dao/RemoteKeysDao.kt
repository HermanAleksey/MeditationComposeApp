package com.example.meditationcomposeapp.data_source.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meditationcomposeapp.data_source.entity.db.RemoteKeys

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_key WHERE beer_id = :id")
    suspend fun getRemoteKeyByBeerID(id: Int): RemoteKeys?

    @Query("DELETE FROM remote_key")
    suspend fun clearRemoteKeys()

    @Query("SELECT created_at FROM remote_key ORDER BY created_at DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}