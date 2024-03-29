package com.justparokq.feature.charts.internal.test_data

import com.justparokq.feature.charts.api.chart.bar.BarChartData
import com.justparokq.feature.charts.api.chart.pie.PieChartData

private fun randomValue(): Float = (100 * Math.random() + 25).toFloat()

internal fun getTestPieChartData() = PieChartData(
    slices = listOf(
        PieChartData.Slice(
            randomValue(),
            GraphColors.getRandomColor()
        ),
        PieChartData.Slice(
            randomValue(),
            GraphColors.getRandomColor()
        ),
        PieChartData.Slice(
            randomValue(),
            GraphColors.getRandomColor()
        )
    ),
    selectedSlice = null
)

internal fun getTestBarChartData(): BarChartData {
    return BarChartData(
        bars = listOf(
            BarChartData.Bar(
                label = "Bar1",
                value = randomValue(),
                color = GraphColors.getRandomColor()
            ),
            BarChartData.Bar(
                label = "Bar2",
                value = randomValue(),
                color = GraphColors.getRandomColor()
            ),
            BarChartData.Bar(
                label = "Bar3",
                value = randomValue(),
                color = GraphColors.getRandomColor()
            )
        )
    )
}