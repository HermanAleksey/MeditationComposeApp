package com.example.beer_sorts.internal.presentation.beer_list

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.beer_sorts.api.BeerListNavRoute
import com.example.common.view_model.NavigationBaseViewModel
import com.example.database.model.BeerListItem
import com.example.punk_source.api.use_case.punk.GetBeerPagingRemoteMediatorUseCase
import com.example.punk_source.api.use_case.punk.db.GetBeerPagingSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BeerListScreenViewModel @Inject constructor(
    getBeerPagingRemoteMediatorUseCase: GetBeerPagingRemoteMediatorUseCase,
    private val getBeerPagingSourceUseCase: GetBeerPagingSourceUseCase,
) : NavigationBaseViewModel<BeerListNavRoute>() {

    @OptIn(ExperimentalPagingApi::class)
    val beersPagingFlow: Flow<PagingData<BeerListItem>> = Pager(
        remoteMediator = getBeerPagingRemoteMediatorUseCase(),
        config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE * 2),
    ) {
        getBeerPagingSourceUseCase()
    }.flow.cachedIn(viewModelScope)

    fun onBeerItemClicked(beerId: Int) {
        _navigationEvent.update {
            BeerListNavRoute.DetailedBeerScreen(beerId)
        }
    }

    companion object {
        private const val PAGE_SIZE = 5
    }
}