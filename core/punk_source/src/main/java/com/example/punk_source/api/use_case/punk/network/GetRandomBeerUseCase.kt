package com.example.punk_source.api.use_case.punk.network

import com.example.core.model.NetworkResponse
import com.example.core.model.beer_sorts.Beer
import com.example.punk_source.api.repository.PunkWebRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetRandomBeerUseCase {
    suspend operator fun invoke(beerId: Int): Flow<NetworkResponse<Beer>>
}

class GetRandomBeerUseCaseImpl @Inject constructor(
    private val punkWebRepository: PunkWebRepository
): GetRandomBeerUseCase {

    override suspend fun invoke(beerId: Int): Flow<NetworkResponse<Beer>> =
        punkWebRepository.getRandomBeer()

}