package com.example.meditationcomposeapp.data_source.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.meditationcomposeapp.data_source.entity.db.BeerDB

@Dao
interface BeerDao {

    @Query("SELECT * FROM beer")
    fun getAll(): List<BeerDB>

    @Query("SELECT * FROM beer WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<BeerDB>

    @Insert
    fun insertAll(vararg beers: BeerDB)

    @Delete
    fun delete(beer: BeerDB)
}