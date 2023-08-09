package com.justparokq.feature.shuffle_puzzle.internal.presentation.composables

import PuzzleGameDescriptionCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.shuffle_puzzle.api.ShufflePuzzleAction
import com.justparokq.feature.shuffle_puzzle.api.ShufflePuzzleState
import com.justparokq.feature.shuffle_puzzle.internal.presentation.composables.puzzle_board.PuzzleBoard
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun PuzzleBoardWithCounter(
    processAction: (ShufflePuzzleAction) -> Unit,
    uiState: State<ShufflePuzzleState>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        PuzzleGameDescriptionCard(
            modifier = Modifier
                .padding(start = 18.dp, end = 18.dp, top = 18.dp)
                .fillMaxWidth()
                .height(150.dp),
            processAction = processAction,
            uiState = uiState
        )

        Spacer(modifier = Modifier.height(10.dp))

        PuzzleBoard(
            puzzle = uiState.value.puzzle,
            onCreatePuzzleClick = { bitmap ->
                processAction(ShufflePuzzleAction.OnCreatePuzzleClick(bitmap))
            },
            onMovePerformed = { success ->
                processAction(ShufflePuzzleAction.OnMovePerformed(success))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
        )
    }
}

@Preview
@Composable
internal fun PuzzleBoardWithCounterPreview() {
    AppTheme {
        PuzzleBoardWithCounter(
            processAction = { },
            uiState = MutableStateFlow(ShufflePuzzleState()).collectAsState(),
        )
    }
}