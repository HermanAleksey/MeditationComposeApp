package com.example.charts.line_chart

import android.graphics.Point
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.charts.line_chart.view_model.LineChartViewState

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: LineChartViewState,
    points: List<Point>,
) {
//    val textMeasurer = rememberTextMeasurer()
    val displayedPoints = points.takeLast(data.viewPort.xCapacity)
    val elementsOffset = if (points.size > data.viewPort.xCapacity) {
        points.size - data.viewPort.xCapacity
    } else 0

    Canvas(
        modifier = modifier,
    ) {
        val workingAreaXAxis = size.width - data.offset * 2
        val workingAreaYAxis = size.height - data.offset * 2
        val xAtomDistance = workingAreaXAxis / data.viewPort.xCapacity
        val yAtomDistance = workingAreaYAxis / data.viewPort.yCapacity

        //draw lines
        drawGraphLines(data, elementsOffset, displayedPoints, xAtomDistance, yAtomDistance)
        //draw points
        if (data.drawPoints) {
            drawPoints(data, elementsOffset, displayedPoints, xAtomDistance, yAtomDistance)
        }
    }

    Canvas(
        modifier = modifier,
        onDraw = {
            //draw axis
            data.axisDrawer.drawAxis(this, data.offset.toFloat())

//            //draw labels
//            drawText(
//                textMeasurer = textMeasurer,
//                text = AnnotatedString("Hello"),
////                topLeft = Offset()
//            )
        }
    )
}

fun DrawScope.drawGraphLines(
    data: LineChartViewState,
    elementsOffset: Int,
    points: List<Point>,
    xAtomDistance: Float,
    yAtomDistance: Float,
) {
    for (pointIndex in 0 until points.lastIndex) {
        val startPoint = points[pointIndex]
        val endPoint = points[pointIndex + 1]
        val startPointOffset = Offset(
            data.offset + (startPoint.x - elementsOffset) * xAtomDistance,
            size.height - data.offset - startPoint.y * yAtomDistance
        )
        val endPointOffset = Offset(
            data.offset + (endPoint.x - elementsOffset) * xAtomDistance,
            size.height - data.offset - endPoint.y * yAtomDistance
        )

        data.lineDrawer.drawLine(
            this,
            startPointOffset = startPointOffset,
            endPointOffset = endPointOffset,
        )
    }
}

fun DrawScope.drawPoints(
    data: LineChartViewState,
    elementsOffset: Int,
    points: List<Point>,
    xAtomDistance: Float,
    yAtomDistance: Float,
) {
    for (pointIndex in 0..points.lastIndex) {
        val pointOffset = Offset(
            data.offset + (points[pointIndex].x - elementsOffset) * xAtomDistance,
            size.height - data.offset - points[pointIndex].y * yAtomDistance
        )
        data.pointDrawer.drawPoint(
            this,
            pointOffset
        )
    }
}