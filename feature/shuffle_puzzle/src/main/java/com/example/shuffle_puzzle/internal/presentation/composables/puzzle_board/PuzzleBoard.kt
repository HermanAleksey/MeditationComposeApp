package com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shuffle_puzzle.api.model.Piece
import com.example.shuffle_puzzle.api.model.Puzzle
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.states.FilledStatePuzzleBoard
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.states.EmptyStatePuzzleBoard

internal const val SELECT_YOUR_IMAGE = -1

@Composable
internal fun PuzzleBoard(
    modifier: Modifier = Modifier,
    puzzle: Puzzle?,
    onMovePerformed: (Boolean) -> Unit,
    onCreatePuzzleClick: (Bitmap) -> Unit,
) {

    Box(modifier = modifier) {
        if (puzzle == null) {
            EmptyStatePuzzleBoard(onCreatePuzzleClick)
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