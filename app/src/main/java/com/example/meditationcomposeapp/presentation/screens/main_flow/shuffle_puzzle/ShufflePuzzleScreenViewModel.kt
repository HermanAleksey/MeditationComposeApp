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

    private var _puzzle: Puzzle? by mutableStateOf(null)

    fun getPuzzle() = _puzzle

    private var state by mutableStateOf(ShufflePuzzleState())

    fun isLoading() = state.isLoading

    fun getMovesDone() = state.movesDone

    fun isPuzzleSolved() = state.isPuzzleSolved

    fun onFirstLunch(puzzleBitmap: Bitmap) {
        state = state.copy(isLoading = true)
        setPuzzleImage(puzzleBitmap)
    }

    private fun setPuzzleImage(puzzleBitmap: Bitmap) {
        _puzzle = Puzzle(3, 3, puzzleBitmap)
        state = state.copy(isLoading = false)
    }

    fun onMovePerformed(movePerformedSuccessfully: Boolean) {
        _puzzle?.apply {
            if (movePerformedSuccessfully) {
                state = state.copy(movesDone = state.movesDone + 1)
                if (checkPuzzleSolved()) onPuzzleSolved()
            }
        }
    }

    private fun onPuzzleSolved() {
        state = state.copy(isPuzzleSolved = true)
    }
}