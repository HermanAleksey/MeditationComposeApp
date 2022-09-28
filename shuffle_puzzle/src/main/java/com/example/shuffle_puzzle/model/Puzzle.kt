package com.example.shuffle_puzzle.model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log


data class Puzzle(
    val columnsAmount: Int,
    val rowsAmount: Int,
    val imageBitmap: Bitmap,
) {
    private var pieceImageWidthPx: Int = 0
    private var pieceImageHeightPx: Int = 0

    val board: Array<Array<Piece>>
    private var emptyPiecePosition: Piece.Position =
        Piece.Position(rowsAmount - 1, columnsAmount - 1)

    init {
        calculatePieceImageSize()

        board = Array(rowsAmount) { row ->
            Array(columnsAmount) { column ->
                val position = Piece.Position(row, column)
                //last item is empty
                val pieceBitmap = if (row == rowsAmount - 1 && column == columnsAmount - 1) {
                    drawEmptyPieceBitmap()
                } else chopImage(position)
                Piece(position, pieceBitmap)
            }
        }
    }

    private fun drawEmptyPieceBitmap(): Bitmap {
        val emptyBitmap = Bitmap.createBitmap(
            pieceImageWidthPx,
            pieceImageHeightPx,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(emptyBitmap)
        val paint = Paint()
        paint.color = Color.YELLOW

        canvas.drawRect(0f,
            0f,
            pieceImageWidthPx.toFloat(),
            pieceImageHeightPx.toFloat(),
            paint
        )
        return emptyBitmap
    }

    private fun calculatePieceImageSize() {
        pieceImageWidthPx = imageBitmap.width / columnsAmount
        pieceImageHeightPx = imageBitmap.height / rowsAmount
    }

    private fun chopImage(position: Piece.Position): Bitmap {
        val startX: Int = position.row * pieceImageWidthPx
        val startY: Int = position.column * pieceImageHeightPx

        return Bitmap.createBitmap(imageBitmap,
            startX,
            startY,
            pieceImageWidthPx,
            pieceImageHeightPx)
    }

    fun switchPieces(piecePosition: Piece.Position): Boolean {
        //switch only if piece is connected to empty one
        if (!emptyPiecePosition.isConnectedTo(piecePosition)) return false

        //wanted position can't be out of the board
        if (piecePosition.column >= columnsAmount || piecePosition.row >= rowsAmount) return false

        val temp = board[piecePosition.row][piecePosition.column]
        board[piecePosition.row][piecePosition.column] =
            board[emptyPiecePosition.row][emptyPiecePosition.column]
        board[emptyPiecePosition.row][emptyPiecePosition.column] = temp

        emptyPiecePosition.apply {
            row = piecePosition.row
            column = piecePosition.column
        }

        return true
    }

    fun checkPuzzleSolved(): Boolean {
        board.forEachIndexed { row, array ->
            array.forEachIndexed { column, piece ->
                if (piece.initialPosition.row != row || piece.initialPosition.column != column) return false
            }
        }
        return true
    }

    //test purposes
    private val currentBitmap: Bitmap
        get() {
            val fullBitmap =
                Bitmap.createBitmap(imageBitmap.width, imageBitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(fullBitmap)

            board.forEachIndexed { row, array ->
                array.forEachIndexed { column, piece ->
                    val startX: Int = row * pieceImageWidthPx
                    val startY: Int = column * pieceImageHeightPx

                    canvas.drawBitmap(piece.imageBitmap, startX.toFloat(), startY.toFloat(), null)
                }
            }
            val solved = checkPuzzleSolved()
            Log.d("TAGG", "printImageBitmap: ")
            return fullBitmap
        }
}