package com.example.punk_source.api.repository

import androidx.paging.PagingSource
import com.example.core.model.beer_sorts.Beer
import com.example.database.model.BeerListItem

interface PunkDBRepository {

    fun getPagingSource(): PagingSource<Int, BeerListItem>

    suspend fun insertAll(beers: List<Beer>)

    suspend fun clear()
}