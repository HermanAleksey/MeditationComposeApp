package com.justparokq.feature.beer_sorts

import com.justparokq.feature.beer_sorts.api.beer_list.BeerListNavRoute
import com.justparokq.feature.beer_sorts.api.beer_list.BeerListScreenViewModel
import com.justparokq.core.coroutines_test.CoroutinesTestRule
import com.justparokq.core.punk_source.api.use_case.punk.GetBeerPagingRemoteMediatorUseCase
import com.justparokq.core.punk_source.api.use_case.punk.db.GetBeerPagingSourceUseCase
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
    fun `onBeerItemClicked, have internet, navigate to detailed beer screen`() = runTest {
        val beerId = 213

        viewModel.onBeerItemClicked(beerId, true)

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

    @Test
    fun `onBeerItemClicked, don't have internet, navigate to detailed beer screen`() = runTest {
        val beerId = 213

        viewModel.onBeerItemClicked(beerId, false)

        val sharedFlowResult = mutableListOf<BeerListNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            BeerListNavRoute.NoInternetScreen
        )
        job.cancel()
    }
}