package com.justparokq.feature.charts.api.chart.bar.renderer.axis

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope

interface XAxisDrawer {
    fun requiredHeight(drawScope: DrawScope): Float

    fun drawAxisLine(
      drawScope: DrawScope,
      canvas: Canvas,
      drawableArea: Rect,
    )
}