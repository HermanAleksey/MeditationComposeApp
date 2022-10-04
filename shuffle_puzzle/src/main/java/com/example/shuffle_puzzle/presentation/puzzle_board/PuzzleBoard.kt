package com.example.shuffle_puzzle.presentation.puzzle_board

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.R
import com.example.shuffle_puzzle.model.Piece
import com.example.shuffle_puzzle.model.Puzzle

@Composable
fun PuzzleBoard(
    puzzle: Puzzle?,
    onPuzzleImageChanged: (Int) -> Unit,
    onMovePerformed: (Boolean) -> Unit,
    onCreatePuzzleClick: () -> Unit,
    modifier: Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        if (puzzle == null) {
            val templatePainters = listOf(
                R.drawable.shuffle_puzzle_template_1,
                R.drawable.shuffle_puzzle_template_2,
                R.drawable.shuffle_puzzle_template_3,
            )

            SelectPuzzleBoard(templatePainters, onPuzzleImageChanged, onCreatePuzzleClick)
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