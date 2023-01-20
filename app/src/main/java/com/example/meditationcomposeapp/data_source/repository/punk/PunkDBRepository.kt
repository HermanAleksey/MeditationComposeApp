package com.example.meditationcomposeapp.data_source.repository.punk

import com.example.meditationcomposeapp.model.entity.beer.Beer

interface PunkDBRepository {

    suspend fun getBeers(offset: Int, limit: Int): List<Beer>

    suspend fun insertAll(beers: List<Beer>)

    suspend fun clear()
}