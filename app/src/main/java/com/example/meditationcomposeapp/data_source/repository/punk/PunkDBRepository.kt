package com.example.meditationcomposeapp.data_source.repository.punk

import androidx.paging.PagingSource
import com.example.meditationcomposeapp.data_source.entity.db.BeerListItem
import com.example.meditationcomposeapp.model.entity.beer.Beer

interface PunkDBRepository {

    suspend fun getBeers(offset: Int, limit: Int): List<Beer>

    fun getPagingSource(): PagingSource<Int, BeerListItem>

    suspend fun insertAll(beers: List<Beer>)

    suspend fun clear()
}