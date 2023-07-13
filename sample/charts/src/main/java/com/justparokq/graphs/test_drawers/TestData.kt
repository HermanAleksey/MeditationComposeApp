package com.justparokq.graphs.test_drawers

import androidx.compose.ui.graphics.Color
import com.justparokq.graphs.lib.pie_chart.PieChartData

fun getTestPieChartData() = PieChartData(
    slices = listOf(
        PieChartData.Slice(20f, Color.Red),
        PieChartData.Slice(30f, Color.Blue),
        PieChartData.Slice(50f, Color.Green)
    )
)