package com.justparokq.feature.charts.api.chart.line.drawers

import androidx.compose.ui.graphics.drawscope.DrawScope

interface IAxisDrawer {

    fun drawAxis(drawScope: DrawScope, offset: Float)
}