package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import com.example.shuffle_puzzle.model.Puzzle

data class ShufflePuzzleState(
    var isLoading: Boolean = false,
    var puzzle: Puzzle? = null,
    var puzzleImageDrawableRes: Int? = null,
    var puzzleSize: Int = 3,
    var movesDone: Int = 0,
    var isPuzzleSolved: Boolean = false,
)