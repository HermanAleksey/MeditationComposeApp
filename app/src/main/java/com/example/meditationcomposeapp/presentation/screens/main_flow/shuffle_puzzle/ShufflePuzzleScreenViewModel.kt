package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shuffle_puzzle.model.Puzzle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class ShufflePuzzleScreenViewModel @Inject constructor() : ViewModel() {

    private var state by mutableStateOf(ShufflePuzzleState())

    fun isLoading() = state.isLoading

    fun isPuzzleCreated() = state.puzzle != null

    fun getMovesDone() = state.movesDone

    fun isPuzzleSolved() = state.isPuzzleSolved

    fun getPuzzle(): Puzzle? = state.puzzle

    fun getPuzzleSize(): Int = state.puzzleSize

    fun onMovePerformed(success: Boolean) {
        if (state.isPuzzleSolved) return

        if (success) state = state.copy(movesDone = state.movesDone + 1)
        if (state.puzzle?.checkPuzzleSolved() == true) onPuzzleSolved()

    }

    private fun onPuzzleSolved() {
        viewModelScope.launch {
            state = state.copy(isPuzzleSolved = true)
        }
    }

    fun onCreatePuzzleClick(bitmap: Bitmap) {
        state = state.copy(
            puzzle = Puzzle(state.puzzleSize, bitmap)
        )
    }

    fun onRestartPuzzleClicked() {
        state = state.copy(
            isPuzzleSolved = false,
            puzzle = null,
            movesDone = 0,
        )
    }

    fun onPuzzleSizeChanged(size: Int) {
        state = state.copy(
            puzzleSize = size
        )
    }
}