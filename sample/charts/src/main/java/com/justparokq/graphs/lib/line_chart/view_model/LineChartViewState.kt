package com.justparokq.graphs.lib.line_chart.view_model

import com.justparokq.graphs.lib.line_chart.drawers.DefaultAxisDrawer
import com.justparokq.graphs.lib.line_chart.drawers.DefaultLineDrawer
import com.justparokq.graphs.lib.line_chart.drawers.DefaultPointDrawer
import com.justparokq.graphs.lib.line_chart.drawers.IAxisDrawer
import com.justparokq.graphs.lib.line_chart.drawers.ILineDrawer
import com.justparokq.graphs.lib.line_chart.drawers.IPointDrawer

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