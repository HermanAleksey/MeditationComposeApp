package com.justparokq.graphs.lib.line_chart.drawers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope

interface IPointDrawer {

    fun drawPoint(drawScope: DrawScope, pointOffset: Offset)
}