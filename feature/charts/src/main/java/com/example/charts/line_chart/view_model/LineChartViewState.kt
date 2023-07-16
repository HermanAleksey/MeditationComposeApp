package com.example.charts.line_chart.view_model

import com.example.charts.line_chart.drawers.DefaultAxisDrawer
import com.example.charts.line_chart.drawers.DefaultLineDrawer
import com.example.charts.line_chart.drawers.DefaultPointDrawer
import com.example.charts.line_chart.drawers.IAxisDrawer
import com.example.charts.line_chart.drawers.ILineDrawer
import com.example.charts.line_chart.drawers.IPointDrawer

data class LineChartViewState(
    val viewPort: ChartViewPort,
    val offset: Int = DEFAULT_OFFSET,
    val drawPoints: Boolean = true,
    val axisDrawer: IAxisDrawer = DefaultAxisDrawer(),
    val pointDrawer: IPointDrawer = DefaultPointDrawer(),
    val lineDrawer: ILineDrawer = DefaultLineDrawer(),
    val autoCapacityX: Boolean = false,
    val autoCapacityY: Boolean = false,
) {

    data class ChartViewPort(
        val xCapacity: Int,
        val yCapacity: Int,
    )

    companion object {
        private const val DEFAULT_OFFSET = 10
    }
}