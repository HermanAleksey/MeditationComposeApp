package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shuffle_puzzle.model.Puzzle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShufflePuzzleScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ShufflePuzzleState())
    val uiState: StateFlow<ShufflePuzzleState> = _uiState

    fun onTimerTick() {
        _uiState.update {
            it.copy(
                solvingTimerSec = _uiState.value.solvingTimerSec + 1
            )
        }
    }

    fun onMovePerformed(success: Boolean) = with(_uiState) {
        if (value.isPuzzleSolved) return

        if (success) update {
            it.copy(movesDone = value.movesDone + 1)
        }

        if (value.puzzle?.checkPuzzleSolved() == true) onPuzzleSolved()
    }

    private fun onPuzzleSolved() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isTimerActive = false,
                    isPuzzleSolved = true
                )
            }
        }
    }

    fun onCreatePuzzleClick(bitmap: Bitmap) {
        _uiState.update {
            it.copy(
                solvingTimerSec = 0,
                isTimerActive = true,
                puzzle = Puzzle(_uiState.value.puzzleSize, bitmap)
            )
        }
    }

    fun onRestartPuzzleClicked() {
        _uiState.update {
            it.copy(
                solvingTimerSec = 0,
                isPuzzleSolved = false,
                puzzle = null,
                movesDone = 0,
            )
        }
    }

    fun onPuzzleSizeChanged(size: Int) {
        _uiState.update {
            it.copy(
                puzzleSize = size
            )
        }
    }
}