package com.example.punk_api.api.repository

import com.example.core.model.beer_sorts.Beer
import com.example.network.NetworkResponse
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