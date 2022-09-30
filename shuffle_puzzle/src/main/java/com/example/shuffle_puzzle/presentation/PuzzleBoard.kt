package com.example.shuffle_puzzle.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
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
        var puzzleBoardHeight by remember {
            mutableStateOf(0.dp)
        }
        var puzzleBoardWidth by remember {
            mutableStateOf(0.dp)
        }
        val localDensity = LocalDensity.current

        Row(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    puzzleBoardHeight = with(localDensity) { it.size.height.toDp() }
                    puzzleBoardWidth = with(localDensity) { it.size.width.toDp() }
                    Log.e("TAGG",
                        "FilledStatePuzzleBoard: height:${it.size.height.dp} width:${it.size.width.dp}")
                }
        ) {

            val pieceHeight = puzzleBoardHeight / puzzle.board.size + 1.dp
            val pieceWidth = puzzleBoardWidth / puzzle.board[0].size + 1.dp
            Log.e("TAGG", "FilledStatePuzzleBoard: pieceHeight:$pieceHeight pieceWidth:$pieceWidth")

            puzzle.board.forEachIndexed { row, array ->
                Spacer(modifier = Modifier.width(1.dp))

                Column(modifier = Modifier.fillMaxHeight()) {
                    array.forEachIndexed { column, piece ->
                        Spacer(modifier = Modifier.height(1.dp))
                        PuzzlePiece(piece = piece.value, height = pieceHeight, width = pieceWidth) {
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