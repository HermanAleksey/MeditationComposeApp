package com.justparokq.mediose.presentation.screens.main_flow.charts_demo

import androidx.compose.runtime.Composable
import com.justparokq.feature.charts.internal.screen.ChartsScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ChartsDemoScreen(
    viewModel: ChartsScreenViewModel,
) {
    com.justparokq.feature.charts.api.screen.ChartsDemoScreen(
        viewModel = viewModel,
    )
}
