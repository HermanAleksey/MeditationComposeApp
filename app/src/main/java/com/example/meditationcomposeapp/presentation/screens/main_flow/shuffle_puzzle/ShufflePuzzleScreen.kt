package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
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
    ColorBackground(color = MaterialTheme.colors.background,
        lockScreenWhenLoading = true,
        isLoading = viewModel.isLoading()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            PuzzleBoardWithCounter(
                isPuzzleCreated = viewModel.isPuzzleCreated(),
                movesDone = viewModel.getMovesDone(),
                onMovePerformed = {
                    viewModel.onMovePerformed(it)
                },
                puzzle = viewModel.getPuzzle(),
                onCreatePuzzleClick = { bitmap ->
                    viewModel.onCreatePuzzleClick(bitmap)
                },
                puzzleSize = viewModel.getPuzzleSize(),
                onPuzzleSizeChanged = {
                    viewModel.onPuzzleSizeChanged(it)
                },
                onRestartPuzzle = {
                    viewModel.onRestartPuzzleClicked()
                },
                timerValueSec = viewModel.getSolvingTimerSec(),
                onTimerSecTick = { viewModel.onTimerTick() },
                isTimerActivated = viewModel.isTimerActive(),
            )
            if (viewModel.isPuzzleSolved()) {
                PuzzleIsSolvedLabel()
            }
        }

    }
}