package com.example.meditationcomposeapp.data_source.repository.punk

import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.beer.Beer
import kotlinx.coroutines.flow.Flow

interface PunkRepository {

    suspend fun getBeerById(
        page: Int,
    ): Flow<NetworkResponse<Beer>>

    suspend fun getRandomBeer(): Flow<NetworkResponse<Beer>>

    suspend fun getBeers(
        page: Int,
        pageSize: Int,
    ): List<Beer>
}