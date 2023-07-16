package com.example.charts.line_chart.drawers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

class DefaultAxisDrawer(
    private val strokeWidth: Float = DEFAULT_STROKE_WIDTH,
    private val color: Color = DEFAULT_COLOR,
) : IAxisDrawer {

    override fun drawAxis(drawScope: DrawScope, offset: Float) {
        with(drawScope) {
            drawLine(
                start = Offset(
                    offset,
                    size.height - offset
                ),
                end = Offset(offset, offset),
                color = color,
                strokeWidth = strokeWidth
            )
            drawLine(
                start = Offset(
                    size.width - offset,
                    size.height - offset
                ),
                end = Offset(offset, size.height - offset),
                color = color,
                strokeWidth = strokeWidth
            )
        }
    }

    companion object {
        private const val DEFAULT_STROKE_WIDTH = 10f
        private val DEFAULT_COLOR = Color.Black
    }
}