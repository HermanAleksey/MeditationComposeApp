package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.meditationcomposeapp.model.entity.beer.Beer
import com.example.meditationcomposeapp.model.usecase.punk.*
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
    insertBeersIntoDBUseCase: InsertBeersIntoDBUseCase,
    clearBeersDBUseCase: ClearBeersDBUseCase,
    getBeersUseCase: GetBeersUseCase,
    private val getBeersFromDBUseCase: GetBeersFromDBUseCase
) : BaseViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val beersPagingFlow: Flow<PagingData<Beer>> = Pager(
        remoteMediator = BeerPagingRemoteMediator(
            pageSize = PAGE_SIZE,
            insertBeersIntoDBUseCase = insertBeersIntoDBUseCase,
            clearBeersDBUseCase = clearBeersDBUseCase,
            getBeersUseCase = getBeersUseCase,
        ),
        config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE)
    ) {
        BeerPagingSource(getBeersFromDBUseCase)
    }.flow.cachedIn(viewModelScope)

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