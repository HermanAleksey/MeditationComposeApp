package com.example.charts.line_chart.drawers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

class DefaultLineDrawer(
    private val color: Color = DEFAULT_COLOR,
    private val strokeWidth: Float = DEFAULT_STROKE_WIDTH,
) : ILineDrawer {

    override fun drawLine(drawScope: DrawScope, startPointOffset: Offset, endPointOffset: Offset) {
        with(drawScope) {
            drawLine(
                start = startPointOffset,
                end = endPointOffset,
                color = color,
                strokeWidth = strokeWidth
            )
        }
    }

    companion object {
        private val DEFAULT_COLOR = Color.Green
        private const val DEFAULT_STROKE_WIDTH = 7f
    }
}
