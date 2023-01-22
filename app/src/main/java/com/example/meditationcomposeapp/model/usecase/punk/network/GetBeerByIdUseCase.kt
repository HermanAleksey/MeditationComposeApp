package com.example.meditationcomposeapp.model.usecase.punk.network

import com.example.meditationcomposeapp.data_source.repository.punk.PunkRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.beer.Beer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetBeerByIdUseCase {
    suspend operator fun invoke(beerId: Int): Flow<NetworkResponse<Beer>>
}

class GetBeerByIdUseCaseImpl @Inject constructor(
    private val punkRepository: PunkRepository
) : GetBeerByIdUseCase {

    override suspend fun invoke(beerId: Int): Flow<NetworkResponse<Beer>> =
        punkRepository.getBeerById(beerId)

}