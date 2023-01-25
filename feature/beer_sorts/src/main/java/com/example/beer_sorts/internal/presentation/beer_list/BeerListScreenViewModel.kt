package com.example.beer_sorts.internal.presentation.beer_list

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.common.view_model.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BeerListScreenViewModel @Inject constructor(
    getBeerPagingRemoteMediatorUseCase: GetBeerPagingRemoteMediatorUseCase,
    private val getBeerPagingSourceUseCase: GetBeerPagingSourceUseCase,
) : BaseViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val beersPagingFlow: Flow<PagingData<BeerListItem>> = Pager(
        remoteMediator = getBeerPagingRemoteMediatorUseCase(),
        config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE * 2),
    ) {
        getBeerPagingSourceUseCase()
    }.flow.cachedIn(viewModelScope)

    fun onBeerItemClicked(beerId: Int) {
        //todo fix
//        _navigationEvent.update {
//            Event(
//                NavigationEvent.Navigate(
//                    DetailedBeerScreenDestination(
//                        beerId
//                    )
//                )
//            )
//        }
    }

    companion object {
        private const val PAGE_SIZE = 5
    }
}