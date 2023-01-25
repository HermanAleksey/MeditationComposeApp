package com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board

import android.graphics.Bitmap
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.internal.model.Piece
import com.example.shuffle_puzzle.internal.model.Puzzle
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.states.FilledStatePuzzleBoard
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.states.SelectPuzzleBoard

const val SELECT_YOUR_IMAGE = -1

@Composable
internal fun PuzzleBoard(
    puzzle: Puzzle?,
    onMovePerformed: (Boolean) -> Unit,
    onCreatePuzzleClick: (Bitmap) -> Unit,
    modifier: Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        if (puzzle == null) {
            SelectPuzzleBoard(onCreatePuzzleClick)
        } else
            FilledStatePuzzleBoard(
                puzzle = puzzle,
                onPieceClicked = { row, column ->
                    val result = puzzle.switchPieces(Piece.Position(row, column))
                    onMovePerformed(result)
                }
            )

    }
}