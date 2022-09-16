package com.example.meditationcomposeapp.model.usecase.random_data

import com.example.meditationcomposeapp.data_source.repository.random_data.RandomDataRepository
import com.example.meditationcomposeapp.model.entity.Beer
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetRandomBeerUseCase {
    suspend operator fun invoke(): Flow<NetworkResponse<Beer>>
}

class GetRandomBeerUseCaseImpl @Inject constructor(
    private val repository: RandomDataRepository
): GetRandomBeerUseCase {
    override suspend fun invoke(): Flow<NetworkResponse<Beer>> =
        repository.getRandomBeer()
}