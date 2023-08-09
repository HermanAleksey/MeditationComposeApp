package com.justparokq.feature.main.api

import androidx.compose.runtime.Composable
import com.justparokq.feature.main.internal.InternalMainScreen

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
) {
    InternalMainScreen(
        processAction = viewModel::processAction
    )
}