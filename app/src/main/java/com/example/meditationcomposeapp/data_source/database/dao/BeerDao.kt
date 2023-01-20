package com.example.meditationcomposeapp.data_source.database.dao

import androidx.room.*
import com.example.meditationcomposeapp.data_source.entity.db.BeerDB

@Dao
interface BeerDao {

    @Query("SELECT * FROM beer LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int): List<BeerDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beers: List<BeerDB>)

    @Query("DELETE FROM beer")
    suspend fun deleteAll()
}