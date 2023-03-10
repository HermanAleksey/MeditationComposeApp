package com.example.beer_sorts

import com.example.beer_sorts.api.BeerListNavRoute
import com.example.beer_sorts.api.BeerListScreenViewModel
import com.example.coroutines_test.CoroutinesTestRule
import com.example.punk_source.api.use_case.punk.GetBeerPagingRemoteMediatorUseCase
import com.example.punk_source.api.use_case.punk.db.GetBeerPagingSourceUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
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
    fun `onBeerItemClicked, navigate to detailed beer screen`() = runTest {
        val beerId = 213

        viewModel.onBeerItemClicked(beerId)

        val sharedFlowResult = mutableListOf<BeerListNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            BeerListNavRoute.DetailedBeerScreen(beerId)
        )
        job.cancel()
    }
}