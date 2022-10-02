package com.example.shuffle_puzzle.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.example.shuffle_puzzle.model.Piece

@Composable
fun PuzzlePiece(piece: Piece, width: Dp, height: Dp, onPieceClick: () -> Unit) {
    Image(
        bitmap = piece.imageBitmap.asImageBitmap(),
        contentDescription = "puzzle piece",
        modifier = Modifier
            .height(height)
            .width(width)
            .clickable {
                onPieceClick()
            },
        contentScale = ContentScale.Crop
    )
}