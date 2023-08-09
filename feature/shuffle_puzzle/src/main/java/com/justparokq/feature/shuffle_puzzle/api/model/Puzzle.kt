package com.justparokq.feature.shuffle_puzzle.api.model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.math.roundToInt


data class Puzzle(
    val size: Int,
    val imageBitmap: Bitmap,
) {
    private var pieceImageWidthPx: Int = 0
    private var pieceImageHeightPx: Int = 0

    val board: Array<Array<MutableState<Piece>>>
    private var emptyPiecePosition: Piece.Position =
        Piece.Position(size - 1, size - 1)

    private lateinit var emptyPieceInitialImage: Bitmap

    init {
        calculatePieceImageSize()

        board = Array(size) { row ->
            Array(size) { column ->
                val position = Piece.Position(row, column)
                //last item is empty
                val pieceBitmap = if (row == size - 1 && column == size - 1) {
                    emptyPieceInitialImage = chopImage(position)
                    drawEmptyPieceBitmap()
                } else chopImage(position)
                mutableStateOf(Piece(position, pieceBitmap))
            }
        }
        shufflePuzzle(size * size * 3)
    }

    private fun drawEmptyPieceBitmap(): Bitmap {
        val emptyBitmap = Bitmap.createBitmap(
            pieceImageWidthPx,
            pieceImageHeightPx,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(emptyBitmap)
        val paint = Paint()
        paint.color = Color.TRANSPARENT

        canvas.drawRect(
            0f,
            0f,
            pieceImageWidthPx.toFloat(),
            pieceImageHeightPx.toFloat(),
            paint
        )
        return emptyBitmap
    }

    private fun calculatePieceImageSize() {
        pieceImageWidthPx = imageBitmap.width / size
        pieceImageHeightPx = imageBitmap.height / size
    }

    private fun chopImage(position: Piece.Position): Bitmap {
        val startX: Int = position.row * pieceImageWidthPx
        val startY: Int = position.column * pieceImageHeightPx

        return Bitmap.createBitmap(
            imageBitmap,
            startX,
            startY,
            pieceImageWidthPx,
            pieceImageHeightPx
        )
    }

    fun switchPieces(piecePosition: Piece.Position): Boolean {
        //switch only if piece is connected to empty one
        if (!emptyPiecePosition.isConnectedTo(piecePosition)) return false

        //wanted position can't be out of the board
        if (piecePosition.column >= size || piecePosition.row >= size) return false

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

    private fun shufflePuzzle(times: Int) {
        repeat(times) {
            performRandomMove()
        }
        if (checkPuzzleSolved()) {
            shufflePuzzle(times)
        }
    }

    private fun performRandomMove() {
        val allowedPositions = mutableListOf<Piece.Position>()
        if (emptyPiecePosition.row + 1 < size)
            allowedPositions.add(
                Piece.Position(
                    emptyPiecePosition.row + 1,
                    emptyPiecePosition.column
                )
            )
        if (emptyPiecePosition.column + 1 < size)
            allowedPositions.add(
                Piece.Position(
                    emptyPiecePosition.row,
                    emptyPiecePosition.column + 1
                )
            )
        if (emptyPiecePosition.row > 0)
            allowedPositions.add(
                Piece.Position(
                    emptyPiecePosition.row - 1,
                    emptyPiecePosition.column
                )
            )
        if (emptyPiecePosition.column > 0)
            allowedPositions.add(
                Piece.Position(
                    emptyPiecePosition.row,
                    emptyPiecePosition.column - 1
                )
            )

        val moveToPerform =
            allowedPositions[(Math.random() * (allowedPositions.size - 1)).roundToInt()]

        switchPieces(moveToPerform)
    }

    fun checkPuzzleSolved(): Boolean {
        board.forEachIndexed { row, array ->
            array.forEachIndexed { column, piece ->
                with(piece.value) {
                    if (initialPosition.row != row || initialPosition.column != column) return false
                }
            }
        }
        replaceRightBottomPieceWithInitialImage()
        return true
    }

    //call only when puzzle is completed
    private fun replaceRightBottomPieceWithInitialImage() {
        board[size - 1][size - 1].value = board[size - 1][size - 1].value.copy(
            imageBitmap = emptyPieceInitialImage
        )
    }
}