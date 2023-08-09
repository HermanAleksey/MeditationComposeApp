package com.justparokq.mediose.presentation.screens.main_flow.shuffle_puzzle

import androidx.compose.runtime.Composable
import com.justparokq.feature.shuffle_puzzle.api.ShufflePuzzleScreen
import com.justparokq.feature.shuffle_puzzle.api.ShufflePuzzleScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ShufflePuzzleScreen(
    viewModel: ShufflePuzzleScreenViewModel,
) {
    ShufflePuzzleScreen(viewModel)
}
