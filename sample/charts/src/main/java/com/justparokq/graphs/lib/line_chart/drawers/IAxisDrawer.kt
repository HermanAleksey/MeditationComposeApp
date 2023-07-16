package com.justparokq.graphs.lib.line_chart.drawers

import androidx.compose.ui.graphics.drawscope.DrawScope

interface IAxisDrawer {

    fun drawAxis(drawScope: DrawScope, offset: Float)
}