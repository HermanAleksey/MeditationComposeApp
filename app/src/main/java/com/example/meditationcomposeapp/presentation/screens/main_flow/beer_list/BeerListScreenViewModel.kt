package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.meditationcomposeapp.model.entity.beer.Beer
import com.example.meditationcomposeapp.model.usecase.punk.GetBeersUseCase
import com.example.meditationcomposeapp.presentation.navigation.Event
import com.example.meditationcomposeapp.presentation.navigation.NavigationEvent
import com.example.meditationcomposeapp.presentation.screens.BaseViewModel
import com.example.meditationcomposeapp.presentation.screens.destinations.DetailedBeerScreenDestination
import com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.paging.BeerPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BeerListScreenViewModel @Inject constructor(
    private val getBeerListUseCase: GetBeersUseCase,
) : BaseViewModel() {

    val beersPagingFlow: Flow<PagingData<Beer>> = Pager(
        pagingSourceFactory = { BeerPagingSource(getBeerListUseCase) },
        config = PagingConfig(pageSize = 5)
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
}