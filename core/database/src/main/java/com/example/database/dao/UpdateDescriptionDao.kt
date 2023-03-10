package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.database.model.UpdateDescriptionDBEntity

@Dao
interface UpdateDescriptionDao {
    @Query("SELECT * FROM updates_log")
    suspend fun getAll(): List<UpdateDescriptionDBEntity>

    @Query("SELECT * FROM updates_log WHERE release_time = (SELECT MAX(release_time) FROM updates_log) ")
    suspend fun getLastUpdate(): UpdateDescriptionDBEntity?

    @Query("UPDATE updates_log SET shown = (:wasShown) WHERE version_name = (:versionName)")
    suspend fun setIsShown(versionName: String, wasShown: Boolean)

    @Insert
    suspend fun insertAll(vararg updates: UpdateDescriptionDBEntity)

    @Delete
    suspend fun delete(update: UpdateDescriptionDBEntity)
}