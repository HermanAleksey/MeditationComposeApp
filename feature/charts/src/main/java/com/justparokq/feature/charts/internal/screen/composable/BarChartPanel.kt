package com.justparokq.feature.charts.internal.screen.composable

import androidx.compose.runtime.Composable
import com.justparokq.feature.charts.api.chart.bar.BarChart
import com.justparokq.feature.charts.api.chart.bar.BarChartData
import com.justparokq.feature.charts.api.chart.bar.renderer.label.SimpleValueDrawer

@Composable
internal fun BarChartPanel(
    barChartData: BarChartData
) {
    BarChart(
        barChartData = barChartData,
        labelDrawer = SimpleValueDrawer(
            drawLocation = SimpleValueDrawer.DrawLocation.Inside
        )
    )
}