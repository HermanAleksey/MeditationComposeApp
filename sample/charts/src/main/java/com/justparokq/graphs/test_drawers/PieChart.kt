package com.justparokq.graphs.test_drawers

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.math.RoundingMode
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt

private const val FULL_CIRCLE_DEGREES: Float = 360.0F

@Composable
fun PieChart(
    pieChartData: PieChartData,
    modifier: Modifier = Modifier,
    animation: AnimationSpec<Float> = TweenSpec(durationMillis = 500),
) {
    val transitionProgress = remember(pieChartData.slices) { Animatable(initialValue = 0f) }

    // When slices value changes we want to re-animated the chart.
    LaunchedEffect(pieChartData.slices) {
        transitionProgress.animateTo(1f, animationSpec = animation)
    }

    var data by remember {
        mutableStateOf(-1)
    }

    DrawChart(
        pieChartData = pieChartData,
        selectedSlice = data,
        selectedSliceChanged = { data++ },
        modifier = modifier.fillMaxSize(),
        animationProgress = transitionProgress.value,
    )
}


@Composable
private fun DrawChart(
    pieChartData: PieChartData,
    selectedSlice: Int,
    selectedSliceChanged: () -> Unit,
    modifier: Modifier,
    animationProgress: Float,
    percentsLabelFormatter: (Float) -> String = { percents ->
        val roundedPercents = percents.toBigDecimal().setScale(1, RoundingMode.DOWN)
        "$roundedPercents%"
    },
) {
    val slices = pieChartData.slices
    val padding = 60f
    val map = mutableMapOf<PieChartData.Slice, Pair<Float, Float>>()

    fun isPointInsideCircle(x: Float, y: Float, a: Float, b: Float, r: Float): Boolean {
        return (x - a).pow(2) + (y - b).pow(2) <= r.pow(2)
    }

    fun segmentLength(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
    }

    var centerX: Float = 0.0f
    var centerY: Float = 0.0f
    var radius: Float = 0.0f
    Canvas(
        modifier = modifier
            .pointerInput(true) {
                detectTapGestures {
                    val inZone = isPointInsideCircle(it.x, it.y, centerX, centerY, radius)
                    if (!inZone) return@detectTapGestures

                    val a = radius
                    val b = segmentLength(it.x, it.y, centerX, centerY)
                    val c = segmentLength(it.x, it.y, centerX - radius, centerY)
                    val angleCos = (a.pow(2) + b.pow(2) - c.pow(2)) / (2 * a * b)
                    var angle = acos(angleCos) * 180 / PI
                    //invert if click in bottom part
                    if (it.y > centerY) {
                        angle = 360f - angle
                    }

                    //getSlice
                    map.forEach { pair ->
                        if (pair.value.first < angle && pair.value.second >= angle) {
                            Log.e("TAGG", "DrawChart: ${pair.key} angle:$angle")
                            pair.key.isSelected = !pair.key.isSelected
                            selectedSliceChanged()
                        }
                    }
                }
            }
    ) {
        drawIntoCanvas { canvas ->
            var currentStartAngle = -180f
            var currentStartAngleWithoutAnimation = 0f
            val anglePerValue = FULL_CIRCLE_DEGREES / pieChartData.totalSize

            slices.forEach { slice ->
                val scale = if (slice.isSelected) 1.1f else 1.0f
                val arcAngleValue = anglePerValue * slice.value
                val arcAngleValueWithProgress = arcAngleValue * animationProgress

                //drawing pie
                val diameter = minOf(size.width, size.height)
                val horizontalOffset = (size.width - diameter) / 2
                val verticalOffset = (size.height - diameter) / 2
                val squareToDrawIn = Rect(
                    left = horizontalOffset + padding,
                    top = verticalOffset + padding,
                    right = size.width - horizontalOffset - padding,
                    bottom = size.height - verticalOffset - padding,
                )

                scale(scale) {
                    canvas.drawArc(
                        rect = squareToDrawIn,
                        paint = Paint().apply {
                            isAntiAlias = true
                            style = PaintingStyle.Fill
                            color = slice.color
                        },
                        startAngle = currentStartAngle,
                        sweepAngle = arcAngleValueWithProgress,
                        useCenter = true
                    )
                }
                map[slice] = Pair(
                    currentStartAngleWithoutAnimation,
                    currentStartAngleWithoutAnimation + arcAngleValue
                )

                //draw labels %
                val arcPercentsValue = (slice.value / pieChartData.totalSize * 100)
                var textRotateAngle = (currentStartAngle + arcAngleValue / 2f) + 90f
                Log.e("TAGG", "DrawChart: $textRotateAngle")

                centerX = diameter / 2 + horizontalOffset
                centerY = diameter / 2 + verticalOffset
                radius = diameter / 2
                val labelOffset = diameter / 4 //half of radius

                rotate(textRotateAngle) {
                    drawContext.canvas.nativeCanvas.drawText(
                        percentsLabelFormatter(arcPercentsValue),
                        centerX,
                        centerY - labelOffset,
                        android.graphics.Paint().apply {
                            textSize = 20.sp.toPx()
                            textAlign = android.graphics.Paint.Align.CENTER
                            color = Color.Black.toArgb()
                        }
                    )
                }

                currentStartAngle += arcAngleValueWithProgress
                currentStartAngleWithoutAnimation += arcAngleValue
            }
        }
    }
}

@Preview
@Composable
fun PieChartPreview() {
    Box(modifier = Modifier.fillMaxWidth()) {
        PieChart(
            pieChartData = PieChartData(
                slices = listOf(
                    PieChartData.Slice(25f, Color.Red),
                    PieChartData.Slice(42f, Color.Blue),
                    PieChartData.Slice(23f, Color.Green)
                )
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}