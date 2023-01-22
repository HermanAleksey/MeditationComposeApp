package com.example.meditationcomposeapp.model.usecase.punk.db

import com.example.meditationcomposeapp.data_source.repository.punk.PunkDBRepository
import com.example.meditationcomposeapp.model.entity.beer.Beer
import javax.inject.Inject


interface GetBeersFromDBUseCase {
    suspend operator fun invoke(offset: Int, limit: Int): List<Beer>
}

class GetBeersFromDBUseCaseImpl @Inject constructor(
    private val punkDbRepository: PunkDBRepository
) : GetBeersFromDBUseCase {

    override suspend fun invoke(offset: Int, limit: Int): List<Beer> =
        punkDbRepository.getBeers(offset = offset, limit = limit)

}