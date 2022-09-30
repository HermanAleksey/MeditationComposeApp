package com.example.shuffle_puzzle.presentation

import PuzzleGameDescriptionCard
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.model.Puzzle

@Composable
fun PuzzleBoardWithCounter(puzzle: Puzzle?, movesDone: Int, onMovePerformed: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 18.dp, end = 18.dp, top = 18.dp)
    ) {
        PuzzleGameDescriptionCard(
            movesDone,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        PuzzleBoard(
            puzzle,
            onMovePerformed,
            modifier = Modifier
                .fillMaxSize()
        )
    }

}