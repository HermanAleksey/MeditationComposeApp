package com.justparokq.graphs.lib.pie_chart

import android.graphics.PointF
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import kotlin.random.Random

private fun randomColor(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)

    return Color(red, green, blue)
}

internal fun DrawScope.drawPieSlice(
    canvas: Canvas,
    scale: Float = 1.0f,
    padding: Int,
    horizontalOffset: Float,
    verticalOffset: Float,
    currentStartAngle: Float,
    arcAngleValue: Float,
    slicePaint: Paint = Paint().apply {
        isAntiAlias = true
        style = PaintingStyle.Fill
        color = randomColor()
    },
) {
    val squareToDrawIn = Rect(
        left = horizontalOffset + padding,
        top = verticalOffset + padding,
        right = size.width - horizontalOffset - padding,
        bottom = size.height - verticalOffset - padding,
    )

    scale(scale) {
        canvas.drawArc(
            rect = squareToDrawIn,
            paint = slicePaint,
            startAngle = currentStartAngle,
            sweepAngle = arcAngleValue,
            useCenter = true
        )
    }
}

/**
 * [textRotateAngle] starts between first and second quarter
 * */
internal fun DrawScope.drawPercentLabel(
    arcPercentsValue: Float,
    labelOffset: Float,
    labelFormatter: (Float) -> String,
    textRotateAngle: Float,
    canvasCenterPoint: PointF,
    labelPaint: android.graphics.Paint = android.graphics.Paint().apply {
        textSize = 20.sp.toPx()
        textAlign = android.graphics.Paint.Align.CENTER
        color = Color.Black.toArgb()
    },
) {
    val isInBottomPart = (textRotateAngle + 90) >= 180
    val y = if (isInBottomPart) {
        canvasCenterPoint.y + labelOffset
    } else {
        canvasCenterPoint.y - labelOffset
    }
    val rotationAngle = if (!isInBottomPart) textRotateAngle else (textRotateAngle - 180)

    rotate(rotationAngle) {
        drawContext.canvas.nativeCanvas.drawText(
            labelFormatter(arcPercentsValue),
            canvasCenterPoint.x,
            y,
            labelPaint
        )
    }
}
