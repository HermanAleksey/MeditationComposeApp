package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.paging.BeerPagingSource
import com.example.meditationcomposeapp.model.entity.beer.Beer
import com.example.meditationcomposeapp.model.usecase.punk.GetBeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BeerListScreenViewModel @Inject constructor(
    private val getBeerListUseCase: GetBeersUseCase
) : ViewModel() {

    private var state by mutableStateOf(BeerListScreenState())

    val beersPagingFlow: Flow<PagingData<Beer>> = Pager(
        pagingSourceFactory = { BeerPagingSource(getBeerListUseCase) },
        config = PagingConfig(pageSize = 5)
    ).flow.cachedIn(viewModelScope)

}