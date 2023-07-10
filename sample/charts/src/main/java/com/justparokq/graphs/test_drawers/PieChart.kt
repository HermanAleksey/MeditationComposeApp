package com.justparokq.graphs.test_drawers

import android.graphics.PointF
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview

private const val FULL_CIRCLE_DEGREES: Float = 360F
private const val INITIAL_STARTING_ANGLE: Float = -180f
private const val DEFAULT_SLIDE_SCALE: Float = 1f

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

    var selectedSlice: PieChartData.Slice? by remember {
        mutableStateOf(null)
    }

    DrawChart(
        pieChartData = pieChartData,
        selectedSlice = selectedSlice,
        selectedSliceChanged = { newSlice ->
            selectedSlice = if (newSlice == selectedSlice) {
                null
            } else {
                newSlice
            }
        },
        modifier = modifier.fillMaxSize(),
        animationProgress = transitionProgress.value,
    )
}

@Composable
private fun DrawChart(
    pieChartData: PieChartData,
    selectedSlice: PieChartData.Slice?,
    selectedSliceChanged: (slice: PieChartData.Slice) -> Unit,
    animationProgress: Float,
    pieChartConfig: PieChartConfig = PieChartConfig(),
    modifier: Modifier,
) {
    val slices = pieChartData.slices
    val slicesToAnglesMap = remember {
        mutableMapOf<PieChartData.Slice, Pair<Float, Float>>()
    }

    var canvasCenterPoint = remember {
        PointF(0.0f, 0.0f)
    }
    var radius: Float = 0.0f
    Canvas(
        modifier = modifier
            .pointerInput(true) {
                detectTapGestures {
                    val inZone = isPointInsideCircle(
                        pointX = it.x,
                        pointY = it.y,
                        circleX = canvasCenterPoint.x,
                        circleY = canvasCenterPoint.y,
                        circleRadius = radius
                    )
                    if (!inZone) return@detectTapGestures

                    var angle = calculateAngleBetweenSegments(
                        segmentALength = radius,
                        segmentBLength = calculateSegmentLength(
                            x1 = it.x,
                            y1 = it.y,
                            x2 = canvasCenterPoint.x,
                            y2 = canvasCenterPoint.y
                        ),
                        segmentCLength = calculateSegmentLength(
                            x1 = it.x,
                            y1 = it.y,
                            x2 = canvasCenterPoint.x - radius,
                            y2 = canvasCenterPoint.y
                        )
                    )

                    //invert angle value if click in bottom part
                    if (it.y > canvasCenterPoint.y) {
                        angle = 360f - angle
                    }

                    //getSlice
                    slicesToAnglesMap.forEach { pair ->
                        if (pair.value.first < angle && pair.value.second >= angle) {
                            selectedSliceChanged(pair.key)
                        }
                    }
                }
            }
    ) {
        drawIntoCanvas { canvas ->
            var currentStartAngle = INITIAL_STARTING_ANGLE
            var currentStartAngleWithoutAnimation = 0f
            val anglePerValue = FULL_CIRCLE_DEGREES / pieChartData.totalSize
            val diameter = minOf(size.width, size.height)
            val horizontalOffset = (size.width - diameter) / 2
            val verticalOffset = (size.height - diameter) / 2
            canvasCenterPoint =
                PointF(diameter / 2 + horizontalOffset, diameter / 2 + verticalOffset)
            radius = diameter / 2

            slices.forEach { slice ->
                val scale = if (slice == selectedSlice)
                    pieChartConfig.selectedSliceScale else DEFAULT_SLIDE_SCALE
                val arcAngleValue = anglePerValue * slice.value
                val arcAngleValueWithProgress = arcAngleValue * animationProgress

                drawPieSlice(
                    canvas = canvas,
                    padding = pieChartConfig.padding,
                    horizontalOffset = horizontalOffset,
                    verticalOffset = verticalOffset,
                    currentStartAngle = currentStartAngle,
                    arcAngleValue = arcAngleValueWithProgress,
                    slicePaint = Paint().apply {
                        isAntiAlias = true
                        style = PaintingStyle.Fill
                        color = slice.color
                    },
                    scale = scale
                )
                slicesToAnglesMap[slice] = Pair(
                    currentStartAngleWithoutAnimation,
                    currentStartAngleWithoutAnimation + arcAngleValue
                )

                //draw labels %
                val arcPercentsValue = (slice.value / pieChartData.totalSize * 100)
                if (arcPercentsValue >= pieChartConfig.showPercentsThreshold) {
                    drawPercentLabel(
                        arcPercentsValue = arcPercentsValue,
                        labelOffset = diameter / 4,
                        labelFormatter = pieChartConfig.percentsLabelFormatter,
                        textRotateAngle = (currentStartAngle + arcAngleValue / 2f) + 90f,
                        canvasCenterPoint = canvasCenterPoint
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