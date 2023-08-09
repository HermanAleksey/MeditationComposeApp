package com.justparokq.feature.shuffle_puzzle.internal.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.justparokq.feature.shuffle_puzzle.api.model.Piece

@Composable
internal fun PuzzlePiece(piece: Piece, sizeDp: Dp, onPieceClick: () -> Unit) {
    Image(
        bitmap = piece.imageBitmap.asImageBitmap(),
        contentDescription = null,
        modifier = Modifier
            .size(sizeDp)
            .clickable {
                onPieceClick()
            },
        contentScale = ContentScale.Crop
    )
}