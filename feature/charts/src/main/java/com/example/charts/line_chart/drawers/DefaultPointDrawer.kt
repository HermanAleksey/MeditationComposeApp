package com.example.charts.line_chart.drawers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

class DefaultPointDrawer(
    private val color: Color = DEFAULT_COLOR,
    private val radius: Float = DEFAULT_RADIUS,
) : IPointDrawer {

    override fun drawPoint(drawScope: DrawScope, pointOffset: Offset) {
        with(drawScope) {
            drawCircle(
                color = color,
                radius = radius,
                center = pointOffset
            )
        }
    }

    companion object {
        private val DEFAULT_COLOR = Color.Blue
        private const val DEFAULT_RADIUS = 6f
    }
}