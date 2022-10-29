package com.example.meditationcomposeapp.data_source.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.meditationcomposeapp.data_source.entity.UpdateDescriptionDBEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UpdateDescriptionDao {
    @Query("SELECT * FROM updates_log")
    suspend fun getAll(): List<UpdateDescriptionDBEntity>

//    @Query("SELECT * FROM updates_log WHERE id IN (:updateIds)")
//    suspend fun loadAllByIds(updateIds: IntArray): Flow<List<UpdateDescriptionDBEntity>>

    @Query("SELECT * FROM updates_log WHERE id = (SELECT MAX(id) FROM updates_log) ")
    suspend fun getLastUpdate(): UpdateDescriptionDBEntity

    @Query("UPDATE updates_log SET shown = (:wasShown) WHERE id = (:updateId)")
    suspend fun setIsShown(updateId: Long, wasShown: Boolean): UpdateDescriptionDBEntity

    @Insert
    suspend fun insertAll(vararg updates: UpdateDescriptionDBEntity)

    @Delete
    suspend fun delete(update: UpdateDescriptionDBEntity)
}