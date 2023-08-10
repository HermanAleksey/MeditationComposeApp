package com.justparokq.feature.charts.api.chart.line.view_model

import com.justparokq.feature.charts.api.chart.line.drawers.DefaultAxisDrawer
import com.justparokq.feature.charts.api.chart.line.drawers.DefaultLineDrawer
import com.justparokq.feature.charts.api.chart.line.drawers.DefaultPointDrawer
import com.justparokq.feature.charts.api.chart.line.drawers.IAxisDrawer
import com.justparokq.feature.charts.api.chart.line.drawers.ILineDrawer
import com.justparokq.feature.charts.api.chart.line.drawers.IPointDrawer

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