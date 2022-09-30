package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle

import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.meditationcomposeapp.R
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
    val bitmap =
        BitmapFactory.decodeResource(
            LocalContext.current.resources,
            R.drawable.puzzle_image
        )
    LaunchedEffect(key1 = true) {
        viewModel.onFirstLunch(bitmap)
    }


    ColorBackground(
        color = MaterialTheme.colors.colorBackground,
        lockScreenWhenLoading = true,
        isLoading = viewModel.isLoading()
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            PuzzleBoardWithCounter(
                onMovePerformed = { movePerformedSuccessfully ->
                    viewModel.onMovePerformed(movePerformedSuccessfully)
                },
                movesDone = viewModel.getMovesDone(),
                puzzle = viewModel.getPuzzle()
            )
            if (viewModel.isPuzzleSolved()) {
                PuzzleIsSolvedLabel()
            }
        }

    }
}