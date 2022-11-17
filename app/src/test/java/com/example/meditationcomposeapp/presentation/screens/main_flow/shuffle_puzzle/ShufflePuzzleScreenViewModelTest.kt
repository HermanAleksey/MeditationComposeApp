package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import android.graphics.Bitmap
import com.example.meditationcomposeapp.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.powermock.reflect.Whitebox

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class ShufflePuzzleScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

    private lateinit var viewModel: ShufflePuzzleScreenViewModel

    @Before
    fun setup() {
        viewModel = ShufflePuzzleScreenViewModel()
    }

    @Test
    fun `init, test initial values`() {
        with(viewModel.uiState.value) {
            assert(!isLoading)
            assert(puzzle == null)
            assert(puzzleSize == 3)
            assert(movesDone == 0)
            assert(!isPuzzleSolved)
            assert(!isTimerActive)
            assert(solvingTimerSec == 0L)
        }
    }

    @Test
    fun `onTimerTick, time increases`() {
        viewModel.onTimerTick()

        assert(viewModel.uiState.value.solvingTimerSec == 1L)

        viewModel.onTimerTick()

        assert(viewModel.uiState.value.solvingTimerSec == 2L)
    }

    @Test
    fun `onMovePerformed, puzzle solved, do nothing`() {
        Whitebox.setInternalState(
            viewModel, "_uiState",
            MutableStateFlow(
                ShufflePuzzleState(
                    isPuzzleSolved = true
                )
            )
        )

        viewModel.onMovePerformed(false)

        with(viewModel.uiState.value) {
            assert(movesDone == 0)
        }
    }
//todo add tests for the rest; mock crateBitmap.
}