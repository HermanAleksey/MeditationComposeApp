package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import com.example.shuffle_puzzle.model.Puzzle

data class ShufflePuzzleState(
    val isLoading: Boolean = false,
    val puzzle: Puzzle? = null,
    val puzzleSize: Int = 3,
    val movesDone: Int = 0,
    val isPuzzleSolved: Boolean = false,
    val isTimerActive: Boolean = false,
    val solvingTimerSec: Long = 0,
)