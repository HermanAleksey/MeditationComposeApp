package com.example.meditationcomposeapp.model.usecase.punk

import com.example.meditationcomposeapp.data_source.repository.punk.PunkRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.beer.Beer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetBeersUseCase {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): Flow<NetworkResponse<List<Beer>>>
}

class GetBeersUseCaseImpl @Inject constructor(
    private val punkRepository: PunkRepository,
) : GetBeersUseCase {

    override suspend fun invoke(
        page: Int,
        pageSize: Int,
    ): Flow<NetworkResponse<List<Beer>>> =
        punkRepository.getBeers(page, pageSize)

}