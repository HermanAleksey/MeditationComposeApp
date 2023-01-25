package com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.states

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.api.model.Puzzle
import com.example.shuffle_puzzle.internal.presentation.composables.PuzzlePiece

@Composable
internal fun FilledStatePuzzleBoard(
    puzzle: Puzzle,
    onPieceClicked: (Int, Int) -> Unit,
) {
    var puzzleBoardWidth by remember {
        mutableStateOf(0.dp)
    }
    val localDensity = LocalDensity.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                puzzleBoardWidth = with(localDensity) { it.size.width.toDp() }
            }
            .aspectRatio(1f)
    ) {
        val pieceSizeDp = puzzleBoardWidth / puzzle.size
        Log.e("TAGG", "FilledStatePuzzleBoard: pieceSizeDp:$pieceSizeDp")

        puzzle.board.forEachIndexed { row, array ->
            if (row > 0)
                Spacer(modifier = Modifier.width(1.dp))

            Column(modifier = Modifier.fillMaxHeight()) {
                array.forEachIndexed { column, piece ->
                    if (column > 0)
                        Spacer(modifier = Modifier.height(1.dp))

                    PuzzlePiece(piece = piece.value, sizeDp = pieceSizeDp) {
                        onPieceClicked(row, column)
                    }
                }

            }

        }
    }
}