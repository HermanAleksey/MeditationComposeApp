package com.justparokq.feature.charts.api.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.justparokq.feature.charts.internal.screen.ChartsScreenViewModel
import com.justparokq.feature.charts.internal.screen.InternalChartsDemoScreen


@Composable
fun ChartsDemoScreen(
    viewModel: ChartsScreenViewModel,
) {
    InternalChartsDemoScreen(
        uiState = viewModel.uiState.collectAsState(),
        processAction = viewModel::processAction
    )
}