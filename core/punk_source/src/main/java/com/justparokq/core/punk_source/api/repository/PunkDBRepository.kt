package com.justparokq.core.punk_source.api.repository

import androidx.paging.PagingSource
import com.justparokq.core.model.beer_sorts.Beer
import com.justparokq.core.database.model.BeerListItem

interface PunkDBRepository {

    fun getPagingSource(): PagingSource<Int, BeerListItem>

    suspend fun insertAll(beers: List<Beer>)

    suspend fun clear()
}