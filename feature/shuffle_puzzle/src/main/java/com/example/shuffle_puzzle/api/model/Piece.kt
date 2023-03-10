package com.example.shuffle_puzzle.api.model

import android.graphics.Bitmap

data class Piece(
    var initialPosition: Position,
    var imageBitmap: Bitmap,
) {
    data class Position(
        var row: Int,
        var column: Int,
    ) {
        fun isConnectedTo(piecePosition: Position): Boolean =
            isConnectedVertically(piecePosition) || isConnectedHorizontally(piecePosition)

        private fun isConnectedVertically(piecePosition: Position): Boolean =
            (this.row + 1 == piecePosition.row
                    || this.row - 1 == piecePosition.row)
                    && this.column == piecePosition.column

        private fun isConnectedHorizontally(piecePosition: Position): Boolean =
            (this.column + 1 == piecePosition.column
                    || this.column - 1 == piecePosition.column)
                    && this.row == piecePosition.row
    }
}
