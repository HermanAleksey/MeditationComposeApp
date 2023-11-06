package com.justparokq.feature.charts.api.chart.bar.renderer.bar

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.justparokq.feature.charts.api.chart.bar.BarChartData

class SimpleBarDrawer : BarDrawer {

    private val barPaint = Paint().apply {
        this.isAntiAlias = true
    }

    override fun drawBar(
        drawScope: DrawScope,
        canvas: Canvas,
        barArea: Rect,
        bar: BarChartData.Bar,
    ) {
        canvas.drawRect(barArea, barPaint.apply {
            color = bar.color
        })
    }
}