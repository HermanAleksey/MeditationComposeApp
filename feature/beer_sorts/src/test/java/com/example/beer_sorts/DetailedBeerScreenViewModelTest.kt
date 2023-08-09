package com.example.beer_sorts

import FakeObjects.getFakeBeer
import com.example.beer_sorts.api.detailed_beer.DetailedBeerScreenViewModel
import com.justparokq.core.model.NetworkResponse
import com.example.coroutines_test.CoroutinesTestRule
import com.justparokq.core.punk_source.api.use_case.punk.network.GetBeerByIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class DetailedBeerScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

    @Mock
    private lateinit var getBeerByIdUseCase: GetBeerByIdUseCase

    private lateinit var viewModel: DetailedBeerScreenViewModel

    @Before
    fun setup() {
        viewModel = DetailedBeerScreenViewModel(
            getBeerByIdUseCase
        )
    }

    @Test
    fun `onScreenOpened, getBeerById success, set beer uiState`() = runTest {
        val beerId = 123
        val beer = getFakeBeer()
        whenever(getBeerByIdUseCase(beerId)).thenReturn(
            flow {
                emit(NetworkResponse.Success(beer))
            }
        )

        viewModel.onScreenOpened(beerId)

        advanceUntilIdle()

        assert(viewModel.uiState.value.beer == beer)
    }

    @Test
    fun `onScreenOpened, getBeerById failure, beer is null`() = runTest {
        val beerId = 123
        whenever(getBeerByIdUseCase(beerId)).thenReturn(
            flow {
                emit(NetworkResponse.Failure(null, ""))
            }
        )

        viewModel.onScreenOpened(beerId)

        advanceUntilIdle()

        assert(viewModel.uiState.value.beer == null)
    }

    @Test
    fun `onScreenOpened, isLoading state at start`() = runTest {
        val beerId = 123
        whenever(getBeerByIdUseCase(beerId)).thenReturn(
            flow {
                emit(NetworkResponse.Loading(true))
            }
        )

        viewModel.onScreenOpened(beerId)

        advanceUntilIdle()

        assert(viewModel.uiState.value.isLoading)
    }

    @Test
    fun `onScreenOpened, getBeerById success, isLoading = false`() = runTest {
        val beerId = 123
        val beer = getFakeBeer()
        whenever(getBeerByIdUseCase(beerId)).thenReturn(
            flow {
                emit(NetworkResponse.Success(beer))
                emit(NetworkResponse.Loading(false))
            }
        )

        viewModel.onScreenOpened(beerId)

        advanceUntilIdle()

        assert(!viewModel.uiState.value.isLoading)
    }

    @Test
    fun `onScreenOpened, getBeerById failure, isLoading = false`() = runTest {
        val beerId = 123
        whenever(getBeerByIdUseCase(beerId)).thenReturn(
            flow {
                emit(NetworkResponse.Failure(null, ""))
                emit(NetworkResponse.Loading(false))
            }
        )

        viewModel.onScreenOpened(beerId)

        advanceUntilIdle()

        assert(!viewModel.uiState.value.isLoading)
    }
}