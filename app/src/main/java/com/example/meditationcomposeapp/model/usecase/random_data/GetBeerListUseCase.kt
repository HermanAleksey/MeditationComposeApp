package com.example.meditationcomposeapp.model.usecase.random_data

import com.example.meditationcomposeapp.data_source.repository.random_data.RandomDataRepository
import com.example.meditationcomposeapp.model.entity.Beer
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetBeerListUseCase {
    suspend operator fun invoke(size: Int): Flow<NetworkResponse<List<Beer>>>
}

class GetBeerListUseCaseImpl @Inject constructor(
    private val repository: RandomDataRepository
) : GetBeerListUseCase {
    override suspend fun invoke(size: Int): Flow<NetworkResponse<List<Beer>>> =
        repository.getBeerList(size)
}