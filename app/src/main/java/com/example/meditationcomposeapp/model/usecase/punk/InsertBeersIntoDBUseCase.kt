package com.example.meditationcomposeapp.model.usecase.punk

import com.example.meditationcomposeapp.data_source.repository.punk.PunkDBRepository
import com.example.meditationcomposeapp.model.entity.beer.Beer
import javax.inject.Inject

interface InsertBeersIntoDBUseCase {

    suspend operator fun invoke(beers: List<Beer>)
}

class InsertBeersIntoDBUseCaseImpl @Inject constructor(
    private val repository: PunkDBRepository
): InsertBeersIntoDBUseCase {
    override suspend fun invoke(beers: List<Beer>) {
        repository.insertAll(beers)
    }

}