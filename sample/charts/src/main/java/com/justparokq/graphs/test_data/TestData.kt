package com.justparokq.graphs.test_data

import android.graphics.Point
import com.justparokq.graphs.lib.bar_chart.BarChartData
import com.justparokq.graphs.lib.pie_chart.PieChartData

private fun randomValue(): Float = (100 * Math.random() + 25).toFloat()

fun getTestLineChartData() = listOf(
    Point(0, 5),
    Point(1, 6),
    Point(2, 7),
    Point(3, 8),
    Point(4, 5),
    Point(5, 9),
    Point(6, 6),
    Point(7, 3),
    Point(8, 2),
)

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