package com.example.meditationcomposeapp.data_source.repository.punk

import com.example.meditationcomposeapp.data_source.database.dao.BeerDao
import com.example.meditationcomposeapp.data_source.entity.db.BeerListItem
import com.example.meditationcomposeapp.data_source.mappers.BidirectionalMapper
import com.example.meditationcomposeapp.model.entity.beer.Beer
import javax.inject.Inject

class PunkDBRepositoryImpl @Inject constructor(
    private val beerDao: BeerDao,
    private val beerMapper: BidirectionalMapper<BeerListItem, Beer>
) : PunkDBRepository {

    override suspend fun getBeers(offset: Int, limit: Int): List<Beer> {
        return beerDao.getAll(offset = offset, limit = limit).map {
            beerMapper.mapTo(it)
        }
    }

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