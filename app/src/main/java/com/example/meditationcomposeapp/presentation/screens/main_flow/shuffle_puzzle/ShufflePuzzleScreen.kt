package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.meditationcomposeapp.presentation.common_composables.ColorBackground
import com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle.composable.PuzzleIsSolvedLabel
import com.example.meditationcomposeapp.ui.theme.colorBackground
import com.example.shuffle_puzzle.presentation.PuzzleBoardWithCounter
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ShufflePuzzleScreen(
    viewModel: ShufflePuzzleScreenViewModel,
) {
    val resources = LocalContext.current.resources

    ColorBackground(color = MaterialTheme.colors.colorBackground,
        lockScreenWhenLoading = true,
        isLoading = viewModel.isLoading()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            PuzzleBoardWithCounter(
                movesDone = viewModel.getMovesDone(),
                onMovePerformed = {
                    viewModel.onMovePerformed(it)
                },
                puzzle = viewModel.getPuzzle(),
                puzzleImageDrawableRes = viewModel.getPuzzleImage(),
                onPuzzleImageSelected = { drawableRes ->
                    viewModel.onPuzzleImageSelected(drawableRes)
                },
                onCreatePuzzleClick = { size, drawableResId ->
                    val bitmap = BitmapFactory.decodeResource(
                        resources,
                        drawableResId
                    )

                    viewModel.onCreatePuzzleClick(size, bitmap)
                },
                onPuzzleSizeChanged = {
                    viewModel.onPuzzleSizeChanged(it)
                },
                onRestartPuzzle = {
                    viewModel.onRestartPuzzle()
                },
                onRefreshPuzzle = {
                    viewModel.onRefreshPuzzle()
                }
            )
            if (viewModel.isPuzzleSolved()) {
                PuzzleIsSolvedLabel()
            }
        }

    }
}