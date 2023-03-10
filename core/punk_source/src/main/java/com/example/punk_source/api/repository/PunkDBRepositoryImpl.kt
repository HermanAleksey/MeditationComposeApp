package com.example.punk_source.api.repository

import androidx.paging.PagingSource
import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Beer
import com.example.database.dao.BeerDao
import com.example.database.model.BeerListItem
import javax.inject.Inject

class PunkDBRepositoryImpl @Inject constructor(
    private val beerDao: BeerDao,
    private val beerMapper: Mapper<BeerListItem, Beer>
) : PunkDBRepository {

    override fun getPagingSource(): PagingSource<Int, BeerListItem> =
        beerDao.pagingSource()

    override suspend fun insertAll(beers: List<Beer>) {
        beerDao.insertAll(
            beers.map {
                beerMapper.mapFrom(it)
            }
        )
    }

    override suspend fun clear() {
        beerDao.clear()
    }
}