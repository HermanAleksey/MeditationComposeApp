package com.example.shuffle_puzzle.api

import androidx.compose.runtime.Composable
import com.example.shuffle_puzzle.internal.presentation.InternalShufflePuzzleScreen

@Composable
fun ShufflePuzzleScreen(
    viewModel: ShufflePuzzleScreenViewModel,
) {
    InternalShufflePuzzleScreen(viewModel)
}