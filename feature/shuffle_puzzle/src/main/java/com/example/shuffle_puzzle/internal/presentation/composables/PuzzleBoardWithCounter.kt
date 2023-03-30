package com.example.shuffle_puzzle.internal.presentation.composables

import PuzzleGameDescriptionCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.AppTheme
import com.example.shuffle_puzzle.api.ShufflePuzzleScreenViewModel
import com.example.shuffle_puzzle.api.model.Puzzle
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.PuzzleBoard

@Composable
internal fun PuzzleBoardWithCounter(
    viewModel: ShufflePuzzleScreenViewModel,
    puzzle: Puzzle?,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        PuzzleGameDescriptionCard(
            viewModel = viewModel,
            modifier = Modifier
                .padding(start = 18.dp, end = 18.dp, top = 18.dp)
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        PuzzleBoard(
            puzzle = puzzle,
            onCreatePuzzleClick = { viewModel.onCreatePuzzleClick(it) },
            onMovePerformed = { success ->
                viewModel.onMovePerformed(success)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
        )
    }
}

@Preview
@Composable
internal fun PuzzleBoardWithCounterPreview() {
    AppTheme {
        PuzzleBoardWithCounter(
            viewModel = ShufflePuzzleScreenViewModel(),
            puzzle = null,
        )
    }
}