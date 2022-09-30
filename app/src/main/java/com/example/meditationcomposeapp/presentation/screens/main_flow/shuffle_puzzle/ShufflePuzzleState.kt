package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

data class ShufflePuzzleState(
    var isLoading: Boolean = false,
    var movesDone: Int = 0,
    var isPuzzleSolved: Boolean = false,
)