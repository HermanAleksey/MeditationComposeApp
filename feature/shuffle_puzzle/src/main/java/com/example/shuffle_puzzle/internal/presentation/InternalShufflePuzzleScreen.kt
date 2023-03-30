package com.example.shuffle_puzzle.internal.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.design_system.common_composables.ColorBackground
import com.example.shuffle_puzzle.api.ShufflePuzzleScreenViewModel
import com.example.shuffle_puzzle.internal.presentation.composables.PuzzleBoardWithCounter
import com.example.shuffle_puzzle.internal.presentation.composables.PuzzleIsSolvedDialog

@Composable
internal fun InternalShufflePuzzleScreen(
    viewModel: ShufflePuzzleScreenViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()

    BackHandler(enabled = uiState.value.isTimerActive, onBack = {
        //back handling
        viewModel.onRestartPuzzleClicked()
    })

    ColorBackground(
        color = MaterialTheme.colors.background,
        lockScreenWhenLoading = true,
        isLoading = uiState.value.isLoading
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PuzzleBoardWithCounter(
                viewModel = viewModel,
                puzzle = uiState.value.puzzle,
            )
            if (uiState.value.isPuzzleSolved) {
                PuzzleIsSolvedDialog(
                    movesDone = uiState.value.movesDone,
                    onResetClick = { viewModel.onRestartPuzzleClicked() }
                )
            }
        }
    }
}