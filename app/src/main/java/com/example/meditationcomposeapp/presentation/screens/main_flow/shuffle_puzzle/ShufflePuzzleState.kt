package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import com.example.shuffle_puzzle.model.Puzzle

data class ShufflePuzzleState(
    var isLoading: Boolean = false,
    var movesDone: Int = 0,
    var puzzle: Puzzle? = null,
    var isPuzzleSolved: Boolean = false,
)