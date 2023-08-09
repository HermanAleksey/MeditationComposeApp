package com.example.shuffle_puzzle.api

import com.justparokq.core.common.mvi.MviState
import com.example.shuffle_puzzle.api.model.Puzzle

data class ShufflePuzzleState(
    val isLoading: Boolean = false,
    val puzzle: Puzzle? = null,
    val puzzleSize: Int = 3,
    val movesDone: Int = 0,
    val isPuzzleSolved: Boolean = false,
    val isTimerActive: Boolean = false,
    val solvingTimerSec: Long = 0,
): MviState