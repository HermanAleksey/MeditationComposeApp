package com.example.shuffle_puzzle.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.example.shuffle_puzzle.model.Piece

@Composable
fun PuzzlePiece(piece: Piece, onPieceClick: () -> Unit) {
    Image(
        bitmap = piece.imageBitmap.asImageBitmap(),
        contentDescription = "puzzle piece",
        modifier = Modifier.clickable {
            onPieceClick()
        }
    )
}