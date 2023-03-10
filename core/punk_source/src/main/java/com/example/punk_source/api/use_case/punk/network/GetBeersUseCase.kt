package com.example.punk_source.api.use_case.punk.network

import com.example.core.model.beer_sorts.Beer
import com.example.punk_source.api.repository.PunkWebRepository
import javax.inject.Inject

interface GetBeersUseCase {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): List<Beer>
}

class GetBeersUseCaseImpl @Inject constructor(
    private val punkRepository: PunkWebRepository,
) : GetBeersUseCase {

    override suspend fun invoke(
        page: Int,
        pageSize: Int,
    ): List<Beer> = punkRepository.getBeers(page, pageSize)

}