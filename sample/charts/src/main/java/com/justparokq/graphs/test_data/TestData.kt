package com.justparokq.graphs.test_data

import com.justparokq.graphs.lib.bar_chart.BarChartData
import com.justparokq.graphs.lib.pie_chart.PieChartData

private fun randomValue(): Float = (100 * Math.random() + 25).toFloat()

fun getTestPieChartData() = PieChartData(
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
    )
)

fun getTestBarChartData(): BarChartData {
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