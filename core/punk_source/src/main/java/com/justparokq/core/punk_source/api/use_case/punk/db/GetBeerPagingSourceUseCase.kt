package com.justparokq.core.punk_source.api.use_case.punk.db

import androidx.paging.PagingSource
import com.justparokq.core.database.model.BeerListItem
import com.justparokq.core.punk_source.api.repository.PunkDBRepository
import javax.inject.Inject

interface GetBeerPagingSourceUseCase {

    operator fun invoke(): PagingSource<Int, BeerListItem>
}

class GetBeerPagingSourceUseCaseImpl @Inject constructor(
    private val repository: PunkDBRepository
) : GetBeerPagingSourceUseCase {

    override fun invoke() = repository.getPagingSource()
}

