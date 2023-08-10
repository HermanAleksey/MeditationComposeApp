package com.justparokq.feature.shuffle_puzzle.internal.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.justparokq.core.design_system.AppTheme
import com.justparokq.core.design_system.common_composables.ColorBackground
import com.justparokq.feature.shuffle_puzzle.api.ShufflePuzzleAction
import com.justparokq.feature.shuffle_puzzle.api.ShufflePuzzleState
import com.justparokq.feature.shuffle_puzzle.internal.presentation.composables.PuzzleBoardWithCounter
import com.justparokq.feature.shuffle_puzzle.internal.presentation.composables.PuzzleIsSolvedDialog
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun InternalShufflePuzzleScreen(
    processAction: (ShufflePuzzleAction) -> Unit,
    uiState: State<ShufflePuzzleState>,
) {
    BackHandler(enabled = uiState.value.isTimerActive, onBack = {
        processAction(ShufflePuzzleAction.OnRestartClicked)
    })

    ColorBackground(
        color = MaterialTheme.colors.background,
        lockScreenWhenLoading = true,
        isLoading = uiState.value.isLoading
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PuzzleBoardWithCounter(
                processAction = processAction,
                uiState = uiState
            )
            if (uiState.value.isPuzzleSolved) {
                PuzzleIsSolvedDialog(
                    movesDone = uiState.value.movesDone,
                    onResetClick = {
                        processAction(ShufflePuzzleAction.OnRestartClicked)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun InternalShufflePuzzleScreenPreview() {
    AppTheme {
        InternalShufflePuzzleScreen(
            processAction = {},
            uiState = MutableStateFlow(ShufflePuzzleState()).collectAsState()
        )
    }
}