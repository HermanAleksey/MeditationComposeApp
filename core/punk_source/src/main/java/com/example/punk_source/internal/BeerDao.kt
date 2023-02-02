package com.example.punk_source.internal

import androidx.paging.PagingSource
import androidx.room.*
import com.example.punk_source.api.model.db.BeerListItem

@Dao
interface BeerDao {

    @Query("SELECT * FROM beer ORDER BY id")
    fun pagingSource(): PagingSource<Int, BeerListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beers: List<BeerListItem>)

    @Query("DELETE FROM beer")
    suspend fun clear()
}