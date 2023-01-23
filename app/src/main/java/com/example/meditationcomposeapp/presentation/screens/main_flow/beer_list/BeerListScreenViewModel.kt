package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.meditationcomposeapp.data_source.entity.db.BeerListItem
import com.example.meditationcomposeapp.model.usecase.punk.*
import com.example.meditationcomposeapp.model.usecase.punk.db.GetBeerPagingSourceUseCase
import com.example.meditationcomposeapp.presentation.navigation.Event
import com.example.meditationcomposeapp.presentation.navigation.NavigationEvent
import com.example.meditationcomposeapp.presentation.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.example.meditationcomposeapp.presentation.screens.destinations.DetailedBeerScreenDestination

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
        _navigationEvent.update {
            Event(
                NavigationEvent.Navigate(
                    DetailedBeerScreenDestination(
                        beerId
                    )
                )
            )
        }
    }

    companion object {
        private const val PAGE_SIZE = 5
    }
}