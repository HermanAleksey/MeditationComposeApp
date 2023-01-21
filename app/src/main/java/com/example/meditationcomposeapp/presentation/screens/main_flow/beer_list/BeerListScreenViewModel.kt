package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.meditationcomposeapp.model.entity.beer.Beer
import com.example.meditationcomposeapp.model.usecase.punk.BeerPagingRemoteMediator
import com.example.meditationcomposeapp.model.usecase.punk.BeerPagingSource
import com.example.meditationcomposeapp.model.usecase.punk.GetBeersUseCase
import com.example.meditationcomposeapp.presentation.navigation.Event
import com.example.meditationcomposeapp.presentation.navigation.NavigationEvent
import com.example.meditationcomposeapp.presentation.screens.BaseViewModel
import com.example.meditationcomposeapp.presentation.screens.destinations.DetailedBeerScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BeerListScreenViewModel @Inject constructor(
    private val getBeerListUseCase: GetBeersUseCase,
) : BaseViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val beersPagingFlow: Flow<PagingData<Beer>> = Pager(
        remoteMediator = BeerPagingRemoteMediator(
            limit =1,
            offset = 1,
            getBeersUseCase =  getBeerListUseCase,

        ),
        pagingSourceFactory = { BeerPagingSource(getBeerListUseCase) },
        config = PagingConfig(pageSize = PAGE_SIZE)
    ).flow.cachedIn(viewModelScope)

    fun onBeerItemClicked(beer: Beer) = _navigationEvent.update {
        Event(
            NavigationEvent.Navigate(
                DetailedBeerScreenDestination(
                    beer
                )
            )
        )
    }

    companion object {
        private const val PAGE_SIZE = 5
    }
}