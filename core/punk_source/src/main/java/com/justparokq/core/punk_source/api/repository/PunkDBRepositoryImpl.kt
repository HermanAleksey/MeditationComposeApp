package com.justparokq.core.punk_source.api.repository

import androidx.paging.PagingSource
import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.database.dao.BeerDao
import com.justparokq.core.database.model.BeerListItem
import com.justparokq.core.model.beer_sorts.Beer
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