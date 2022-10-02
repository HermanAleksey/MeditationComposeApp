package com.example.shuffle_puzzle.presentation.puzzle_board

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.model.Piece
import com.example.shuffle_puzzle.model.Puzzle
import com.example.shuffle_puzzle.presentation.PuzzlePiece

@Composable
fun FilledStatePuzzleBoard(puzzle: Puzzle, onPieceClicked: (Int, Int) -> Unit) {
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
            }
            .aspectRatio(1f)
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
                        onPieceClicked(row, column)
                    }
                }

            }

        }
    }
}