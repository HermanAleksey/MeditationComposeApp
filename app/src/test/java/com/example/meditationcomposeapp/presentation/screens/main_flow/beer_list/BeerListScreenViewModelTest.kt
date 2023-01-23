package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import com.example.meditationcomposeapp.CoroutinesTestRule
import com.example.meditationcomposeapp.model.usecase.punk.GetBeerPagingRemoteMediatorUseCase
import com.example.meditationcomposeapp.model.usecase.punk.db.GetBeerPagingSourceUseCase
import com.example.meditationcomposeapp.presentation.navigation.NavigationEvent
import com.example.meditationcomposeapp.presentation.screens.destinations.DetailedBeerScreenDestination
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class BeerListScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

    @Mock
    private lateinit var getBeerPagingRemoteMediatorUseCase: GetBeerPagingRemoteMediatorUseCase

    @Mock
    private lateinit var getBeerPagingSourceUseCase: GetBeerPagingSourceUseCase

    private lateinit var viewModel: BeerListScreenViewModel

    @Before
    fun setup() {
        viewModel = BeerListScreenViewModel(
            getBeerPagingRemoteMediatorUseCase,
            getBeerPagingSourceUseCase
        )
    }

    @Test
    fun `onBeerItemClicked, navigate to detailed beer screen`(){
        val beerId = 213

        viewModel.onBeerItemClicked(beerId)

        assertEquals(
            NavigationEvent.Navigate(
                DetailedBeerScreenDestination(beerId)
            ).toString(),
            viewModel.navigationEvent.value.getNavigationIfNotHandled().toString()
        )
    }
}