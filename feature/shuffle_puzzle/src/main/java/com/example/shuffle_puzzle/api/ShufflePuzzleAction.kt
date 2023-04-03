package com.example.shuffle_puzzle.api

import android.graphics.Bitmap
import com.example.common.mvi.MviAction

internal sealed interface ShufflePuzzleAction : MviAction {

    object OnRestartClicked : ShufflePuzzleAction

    data class OnCreatePuzzleClick(
        val bitmap: Bitmap,
    ) : ShufflePuzzleAction

    data class OnMovePerformed(
        val success: Boolean,
    ) : ShufflePuzzleAction

    data class OnPuzzleSizeChanged(
        val size: Int,
    ) : ShufflePuzzleAction
}
