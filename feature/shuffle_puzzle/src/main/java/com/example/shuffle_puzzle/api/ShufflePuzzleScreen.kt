package com.example.shuffle_puzzle.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.shuffle_puzzle.internal.presentation.InternalShufflePuzzleScreen

@Composable
fun ShufflePuzzleScreen(
    viewModel: ShufflePuzzleScreenViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()

    InternalShufflePuzzleScreen(
        uiState = uiState,
        processAction = viewModel::processAction
    )
}