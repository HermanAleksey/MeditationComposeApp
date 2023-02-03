package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import com.example.meditationcomposeapp.CoroutinesTestRule
import com.example.punk_source.api.use_case.GetBeerPagingRemoteMediatorUseCase
import com.example.punk_source.api.use_case.GetBeerPagingSourceUseCase
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
    private lateinit var getBeerPagingRemoteMediatorUseCase: com.example.punk_source.api.use_case.GetBeerPagingRemoteMediatorUseCase

    @Mock
    private lateinit var getBeerPagingSourceUseCase: com.example.punk_source.api.use_case.GetBeerPagingSourceUseCase

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