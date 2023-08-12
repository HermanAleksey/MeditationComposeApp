package com.justparokq.feature.charts.api.chart.pie

import androidx.compose.ui.graphics.Color

data class PieChartData(
    val slices: List<Slice>,
    val selectedSlice: Slice?,
) {
    internal val totalSize: Float
        get() {
            var total = 0f
            slices.forEach { total += it.value }
            return total
        }

    data class Slice(
        val value: Float,
        val color: Color,
    )
}