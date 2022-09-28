package com.example.shuffle_puzzle.presentation

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap

@Composable
fun PuzzlePiece(imageBitmap: Bitmap, onPieceClick: () -> Unit) {
    Image(
        bitmap = imageBitmap.asImageBitmap(),
        contentDescription = "puzzle piece",
        modifier = Modifier.clickable {
            onPieceClick()
        }
    )
}