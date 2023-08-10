package com.justparokq.feature.charts.internal.screen.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.justparokq.feature.charts.api.chart.bar.BarChart
import com.justparokq.feature.charts.api.chart.bar.renderer.label.SimpleValueDrawer
import com.justparokq.feature.charts.internal.test_data.getTestBarChartData

@Composable
internal fun BarChartPanel() {
    val barChartData = remember {
        getTestBarChartData()
    }

    BarChart(
        barChartData = barChartData,
        labelDrawer = SimpleValueDrawer(
            drawLocation = SimpleValueDrawer.DrawLocation.Inside
        )
    )
}