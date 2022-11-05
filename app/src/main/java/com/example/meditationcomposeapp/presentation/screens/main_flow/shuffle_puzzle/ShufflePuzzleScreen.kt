package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.meditationcomposeapp.presentation.common_composables.ColorBackground
import com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle.composable.PuzzleIsSolvedLabel
import com.example.shuffle_puzzle.presentation.PuzzleBoardWithCounter
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ShufflePuzzleScreen(
    viewModel: ShufflePuzzleScreenViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()

    ColorBackground(
        color = MaterialTheme.colors.background,
        lockScreenWhenLoading = true,
        isLoading = uiState.value.isLoading
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            PuzzleBoardWithCounter(
                isPuzzleCreated = uiState.value.puzzle != null,
                movesDone = uiState.value.movesDone,
                onMovePerformed = {
                    viewModel.onMovePerformed(it)
                },
                puzzle = uiState.value.puzzle,
                onCreatePuzzleClick = { bitmap ->
                    viewModel.onCreatePuzzleClick(bitmap)
                },
                puzzleSize = uiState.value.puzzleSize,
                onPuzzleSizeChanged = {
                    viewModel.onPuzzleSizeChanged(it)
                },
                onRestartPuzzle = {
                    viewModel.onRestartPuzzleClicked()
                },
                timerValueSec = uiState.value.solvingTimerSec,
                onTimerSecTick = { viewModel.onTimerTick() },
                isTimerActivated = uiState.value.isTimerActive,
            )
            if (uiState.value.isPuzzleSolved) {
                PuzzleIsSolvedLabel()
            }
        }
    }
}