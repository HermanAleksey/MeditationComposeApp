package com.justparokq.feature.shuffle_puzzle.internal.presentation.composables.puzzle_board.states

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.shuffle_puzzle.api.model.Puzzle
import com.justparokq.feature.shuffle_puzzle.internal.presentation.composables.PuzzlePiece

@Composable
internal fun FilledStatePuzzleBoard(
    puzzle: Puzzle,
    onPieceClicked: (Int, Int) -> Unit,
) {
    var puzzleBoardWidth by remember {
        mutableStateOf(0.dp)
    }
    val localDensity = LocalDensity.current

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(MaterialTheme.colors.background),
            shape = RoundedCornerShape(8.dp),
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    puzzleBoardWidth = with(localDensity) { it.size.width.toDp() }
                }
                .aspectRatio(1f)) {
                val pieceSizeDp = puzzleBoardWidth / puzzle.size

                puzzle.board.forEachIndexed { row, array ->
                    if (row > 0) Spacer(modifier = Modifier.width(1.dp))

                    Column(modifier = Modifier.fillMaxHeight()) {
                        array.forEachIndexed { column, piece ->
                            if (column > 0) Spacer(modifier = Modifier.height(1.dp))

                            PuzzlePiece(piece = piece.value, sizeDp = pieceSizeDp) {
                                onPieceClicked(row, column)
                            }
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
internal fun FilledStatePuzzleBoardPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxWidth()
        ) {
            FilledStatePuzzleBoard(
                puzzle = Puzzle(
                    4, Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
                ), onPieceClicked = { _, _ -> }
            )
        }

    }
}