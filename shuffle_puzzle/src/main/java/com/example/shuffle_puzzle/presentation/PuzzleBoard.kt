package com.example.shuffle_puzzle.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.model.Puzzle

@Composable
fun PuzzleBoard(puzzle: Puzzle?, movesDone: Int, onMovePerformed: (Int, Int) -> Unit) {
    if (puzzle == null)
        EmptyStatePuzzleBoard()
    else
        Column(modifier = Modifier.fillMaxSize()) {
            MoveCounter(movesDone)
            Row(modifier = Modifier.fillMaxSize()) {
                puzzle.board.forEachIndexed { row, array ->

                    Spacer(modifier = Modifier.width(1.dp))

                    Column(modifier = Modifier.fillMaxHeight()) {
                        array.forEachIndexed { column, piece ->
                            Spacer(modifier = Modifier.height(1.dp))
                            PuzzlePiece(piece.imageBitmap) { onMovePerformed(row, column) }
                        }

                    }

                }
            }
        }
}

@Composable
fun EmptyStatePuzzleBoard() {
    Text(text = stringResource(id = com.example.shuffle_puzzle.R.string.no_puzzle_loaded))
}

@Composable
fun MoveCounter(movesDone: Int) {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Text(text = movesDone.toString())
    }
}