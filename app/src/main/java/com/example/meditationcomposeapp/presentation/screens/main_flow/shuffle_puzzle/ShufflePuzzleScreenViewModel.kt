package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.shuffle_puzzle.model.Puzzle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShufflePuzzleScreenViewModel @Inject constructor() : ViewModel() {

    private var state by mutableStateOf(ShufflePuzzleState())

    fun isLoading() = state.isLoading

    fun getMovesDone() = state.movesDone

    fun isPuzzleSolved() = state.isPuzzleSolved

    fun getPuzzle(): Puzzle? = state.puzzle

    fun getPuzzleImage(): Int? = state.puzzleImageDrawableRes

    fun onMovePerformed(success: Boolean) {
        if (success) state = state.copy(movesDone = state.movesDone + 1)
        if (state.puzzle?.checkPuzzleSolved() == true) onPuzzleSolved()

    }

    private fun onPuzzleSolved() {
        state = state.copy(isPuzzleSolved = true)
    }

    fun onCreatePuzzleClick(size: Int, bitmap: Bitmap) {
        state = state.copy(
            puzzle = Puzzle(size, bitmap)
        )
    }

    fun onRefreshPuzzle() {
        state = state.copy(
            puzzle = state.puzzle?.apply {
                shufflePuzzle(10)
            }
        )
    }

    fun onRestartPuzzle() {
        state.puzzleImageDrawableRes = null
        state.puzzle = null
    }

    fun onPuzzleSizeChanged(size: Int) {
        state = state.copy(
            puzzleSize = size
        )
    }

    fun onPuzzleImageSelected(drawableRes: Int?) {
        state = state.copy(
            puzzleImageDrawableRes = drawableRes
        )
    }
}