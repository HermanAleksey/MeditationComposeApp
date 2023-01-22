package com.example.meditationcomposeapp.model.usecase.punk.db

import androidx.paging.PagingSource
import com.example.meditationcomposeapp.data_source.entity.db.BeerListItem
import com.example.meditationcomposeapp.data_source.repository.punk.PunkDBRepository
import javax.inject.Inject

interface GetBeerPagingSourceUseCase {

    operator fun invoke(): PagingSource<Int, BeerListItem>
}

class GetBeerPagingSourceUseCaseImpl @Inject constructor(
    private val repository: PunkDBRepository
) : GetBeerPagingSourceUseCase {

    override fun invoke() = repository.getPagingSource()
}

