package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.shuffle_puzzle.model.Piece
import com.example.shuffle_puzzle.model.Puzzle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShufflePuzzleScreenViewModel @Inject constructor() : ViewModel() {

    private var state by mutableStateOf(ShufflePuzzleState())

    fun isLoading() = state.isLoading

    fun getMovesDone() = state.movesDone

    fun getPuzzle() = state.puzzle

    fun isPuzzleSolved() = state.isPuzzleSolved

    fun onFirstLunch(puzzleBitmap: Bitmap) {
        state = state.copy(isLoading = true)
        setPuzzleImage(puzzleBitmap)
    }

    private fun setPuzzleImage(puzzleBitmap: Bitmap) {
        state.puzzle = Puzzle(4, 4, puzzleBitmap)
        state = state.copy(isLoading = false)
    }

    fun onMovePerformed(row: Int, column: Int) {
        state.puzzle?.apply {
            val movePerformedSuccessfully = switchPieces(Piece.Position(row, column))

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