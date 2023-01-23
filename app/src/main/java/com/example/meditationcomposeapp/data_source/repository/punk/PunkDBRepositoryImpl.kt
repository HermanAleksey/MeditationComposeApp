package com.example.meditationcomposeapp.data_source.repository.punk

import androidx.paging.PagingSource
import com.example.meditationcomposeapp.data_source.database.dao.BeerDao
import com.example.meditationcomposeapp.data_source.entity.db.BeerListItem
import com.example.meditationcomposeapp.data_source.mappers.BidirectionalMapper
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Beer
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