package com.example.shuffle_puzzle.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.R
import com.example.shuffle_puzzle.model.Piece
import com.example.shuffle_puzzle.model.Puzzle

@Composable
fun PuzzleBoard(puzzle: Puzzle?, onMovePerformed: (Boolean) -> Unit, modifier: Modifier) {
    if (puzzle == null)
        EmptyStatePuzzleBoard()
    else
        FilledStatePuzzleBoard(puzzle, onMovePerformed, modifier)
}

@Composable
fun FilledStatePuzzleBoard(puzzle: Puzzle, onMovePerformed: (Boolean) -> Unit, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            puzzle.board.forEachIndexed { row, array ->

                Spacer(modifier = Modifier.width(1.dp))

                Column(modifier = Modifier.fillMaxHeight()) {
                    array.forEachIndexed { column, piece ->
                        Spacer(modifier = Modifier.height(1.dp))
                        PuzzlePiece(piece.value) {
                            val result = puzzle.switchPieces(Piece.Position(row, column))
                            onMovePerformed(result)
                        }
                    }

                }

            }
        }
    }
}

@Composable
fun EmptyStatePuzzleBoard() {
    Text(text = stringResource(id = R.string.no_puzzle_loaded))
}