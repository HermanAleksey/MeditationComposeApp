package com.justparokq.feature.charts.internal.screen.composable

import android.graphics.Point
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.justparokq.feature.charts.api.chart.line.LineChart
import com.justparokq.feature.charts.api.chart.line.view_model.LineChartViewState

@Composable
internal fun LineChartPanel(
    points: List<Point>,
    state: LineChartViewState,
) {
    LineChart(
        data = state,
        points = points,
        modifier = Modifier.fillMaxSize()
    )
}