package com.example.shuffle_puzzle.presentation

import PuzzleGameDescriptionCard
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.model.Puzzle
import com.example.shuffle_puzzle.presentation.puzzle_board.PuzzleBoard

@Composable
fun PuzzleBoardWithCounter(movesDone: Int, onMovePerformed: (Boolean) -> Unit) {
    var puzzleImageDrawableRes: Int? by remember {
        mutableStateOf(null)
    }
    var puzzle: Puzzle? by remember {
        mutableStateOf(null)
    }
    var columnsAmount by remember {
        mutableStateOf(3)
    }
    var rowsAmount by remember {
        mutableStateOf(3)
    }
    val resources = LocalContext.current.resources
    fun onPuzzleImageSelected(imageRes: Int) {
        puzzleImageDrawableRes = imageRes

        val bitmap = BitmapFactory.decodeResource(
            resources,
            imageRes
        )

        puzzle = Puzzle(columnsAmount, rowsAmount, bitmap)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 18.dp, end = 18.dp, top = 18.dp)
    ) {
        PuzzleGameDescriptionCard(
            puzzleImageDrawableRes,
            movesDone,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        PuzzleBoard(
            puzzle = puzzle,
            onPuzzleImageSelected = ::onPuzzleImageSelected,
            onMovePerformed = onMovePerformed,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 18.dp)
        )
    }

}