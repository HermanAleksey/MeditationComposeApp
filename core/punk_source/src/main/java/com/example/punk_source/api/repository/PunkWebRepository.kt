package com.example.punk_source.api.repository

import com.example.core.model.beer_sorts.Beer
import com.example.core.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface PunkWebRepository {

    suspend fun getBeerById(
        page: Int,
    ): Flow<NetworkResponse<Beer>>

    suspend fun getRandomBeer(): Flow<NetworkResponse<Beer>>

    suspend fun getBeers(
        page: Int,
        pageSize: Int,
    ): List<Beer>
}