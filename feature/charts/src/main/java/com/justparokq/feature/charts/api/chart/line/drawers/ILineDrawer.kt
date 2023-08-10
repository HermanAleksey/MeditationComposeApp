package com.justparokq.feature.charts.api.chart.line.drawers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope

interface ILineDrawer {

    fun drawLine(drawScope: DrawScope, startPointOffset: Offset, endPointOffset: Offset)
}