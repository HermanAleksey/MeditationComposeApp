package com.example.meditationcomposeapp.model.usecase.punk.network

import com.example.meditationcomposeapp.model.entity.beer.Beer
import javax.inject.Inject

interface GetBeersUseCase {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): List<Beer>
}

class GetBeersUseCaseImpl @Inject constructor(
    private val punkRepository: PunkRepository,
) : GetBeersUseCase {

    override suspend fun invoke(
        page: Int,
        pageSize: Int,
    ): List<Beer> = punkRepository.getBeers(page, pageSize)

}