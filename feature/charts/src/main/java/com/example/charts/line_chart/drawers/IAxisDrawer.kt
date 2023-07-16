package com.example.charts.line_chart.drawers

import androidx.compose.ui.graphics.drawscope.DrawScope

interface IAxisDrawer {

    fun drawAxis(drawScope: DrawScope, offset: Float)
}