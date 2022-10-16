package com.example.meditationcomposeapp.data_source.repository.random_data

import com.example.meditationcomposeapp.model.entity.beer.Beer
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface RandomDataRepository {

    fun getRandomBeer(): Flow<NetworkResponse<Beer>>

    fun getBeerList(size: Int): Flow<NetworkResponse<List<Beer>>>
}