package com.example.shuffle_puzzle.presentation

import PuzzleGameDescriptionCard
import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.R
import com.example.shuffle_puzzle.model.Puzzle
import com.example.shuffle_puzzle.presentation.puzzle_board.PuzzleBoard

@Composable
fun PuzzleBoardWithCounter(
    isPuzzleCreated: Boolean,
    movesDone: Int,
    onMovePerformed: (success: Boolean) -> Unit,
    puzzle: Puzzle?,
    onCreatePuzzleClick: (Bitmap) -> Unit,
    puzzleSize: Int,
    onPuzzleSizeChanged: (size: Int) -> Unit,
    onRestartPuzzle: () -> Unit,
    onRefreshPuzzle: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 18.dp, end = 18.dp, top = 18.dp)
    ) {
        PuzzleGameDescriptionCard(
            isPuzzleCreated = isPuzzleCreated,
            puzzleImageBitmap = puzzle?.imageBitmap,
            movesDone = movesDone,
            restartPuzzle = onRestartPuzzle,
            refreshPuzzle = onRefreshPuzzle,
            puzzleSize = puzzleSize,
            updateSelectedSizeValue = onPuzzleSizeChanged,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.puzzle_game_description_height))
        )
        Spacer(modifier = Modifier.height(10.dp))

        PuzzleBoard(
            puzzle = puzzle,
            onCreatePuzzleClick = onCreatePuzzleClick,
            onMovePerformed = { success ->
                onMovePerformed(success)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
        )
    }

}