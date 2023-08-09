package com.justparokq.core.punk_source.api.use_case.punk.network

import com.justparokq.core.model.NetworkResponse
import com.justparokq.core.model.beer_sorts.Beer
import com.justparokq.core.punk_source.api.repository.PunkWebRepository
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