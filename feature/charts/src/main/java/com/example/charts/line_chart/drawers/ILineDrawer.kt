package com.example.charts.line_chart.drawers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope

interface ILineDrawer {

    fun drawLine(drawScope: DrawScope, startPointOffset: Offset, endPointOffset: Offset)
}