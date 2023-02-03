package com.example.punk_source.api.use_case.punk.db

import com.example.core.model.beer_sorts.Beer
import com.example.punk_source.api.repository.PunkDBRepository
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