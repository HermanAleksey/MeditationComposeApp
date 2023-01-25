package com.example.meditationcomposeapp.presentation.screens.main_flow

import androidx.compose.runtime.Composable
import com.example.shuffle_puzzle.api.ShufflePuzzleScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.example.shuffle_puzzle.api.ShufflePuzzleScreen

@Destination
@Composable
fun ShufflePuzzleScreen(
    viewModel: ShufflePuzzleScreenViewModel,
) {
    ShufflePuzzleScreen(viewModel)
}