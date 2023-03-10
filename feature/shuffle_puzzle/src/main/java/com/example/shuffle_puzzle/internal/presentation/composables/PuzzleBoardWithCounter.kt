package com.example.shuffle_puzzle.internal.presentation.composables

import PuzzleGameDescriptionCard
import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.api.model.Puzzle
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.PuzzleBoard

@Composable
internal fun PuzzleBoardWithCounter(
    isPuzzleCreated: Boolean,
    movesDone: Int,
    onMovePerformed: (success: Boolean) -> Unit,
    puzzle: Puzzle?,
    onCreatePuzzleClick: (Bitmap) -> Unit,
    puzzleSize: Int,
    onPuzzleSizeChanged: (size: Int) -> Unit,
    onRestartPuzzle: () -> Unit,
    timerValueSec: Long,
    onTimerSecTick: () -> Unit,
    isTimerActivated: Boolean,
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
            onRestartPuzzle = onRestartPuzzle,
            puzzleSize = puzzleSize,
            updateSelectedSizeValue = onPuzzleSizeChanged,
            timerValueSec = timerValueSec,
            onTimerSecTick = onTimerSecTick,
            isTimerActivated = isTimerActivated,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
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