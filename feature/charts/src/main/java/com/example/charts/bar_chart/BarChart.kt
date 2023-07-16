package com.example.charts.bar_chart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.example.charts.bar_chart.BarChartUtils.axisAreas
import com.example.charts.bar_chart.BarChartUtils.barDrawableArea
import com.example.charts.bar_chart.BarChartUtils.forEachWithArea
import com.example.charts.bar_chart.renderer.bar.BarDrawer
import com.example.charts.bar_chart.renderer.bar.SimpleBarDrawer
import com.example.charts.bar_chart.renderer.label.LabelDrawer
import com.example.charts.bar_chart.renderer.label.SimpleValueDrawer
import com.example.charts.bar_chart.renderer.xaxis.SimpleXAxisDrawer
import com.example.charts.bar_chart.renderer.xaxis.XAxisDrawer
import com.example.charts.bar_chart.renderer.yaxis.SimpleYAxisDrawer
import com.example.charts.bar_chart.renderer.yaxis.YAxisDrawer

@Composable
fun BarChart(
    barChartData: BarChartData,
    modifier: Modifier = Modifier,
    animation: AnimationSpec<Float> = TweenSpec(durationMillis = 500),
    barDrawer: BarDrawer = SimpleBarDrawer(),
    xAxisDrawer: XAxisDrawer = SimpleXAxisDrawer(),
    yAxisDrawer: YAxisDrawer = SimpleYAxisDrawer(),
    labelDrawer: LabelDrawer = SimpleValueDrawer(),
) {
    val transitionAnimation = remember(barChartData.bars) { Animatable(initialValue = 0f) }

    LaunchedEffect(barChartData.bars) {
        transitionAnimation.animateTo(1f, animationSpec = animation)
    }

    val progress = transitionAnimation.value

    Canvas(modifier = modifier
        .fillMaxSize()
        .drawBehind {
            drawIntoCanvas { canvas ->
                val (xAxisArea, yAxisArea) = axisAreas(
                    drawScope = this,
                    totalSize = size,
                    xAxisDrawer = xAxisDrawer,
                    labelDrawer = labelDrawer
                )
                val barDrawableArea = barDrawableArea(xAxisArea)

                // Draw yAxis line.
                yAxisDrawer.drawAxisLine(
                    drawScope = this,
                    canvas = canvas,
                    drawableArea = yAxisArea
                )

                // Draw xAxis line.
                xAxisDrawer.drawAxisLine(
                    drawScope = this,
                    canvas = canvas,
                    drawableArea = xAxisArea
                )
                // Draw each bar.
                barChartData.forEachWithArea(
                    this,
                    barDrawableArea,
                    progress,
                    labelDrawer
                ) { barArea, bar ->
                    barDrawer.drawBar(
                        drawScope = this,
                        canvas = canvas,
                        barArea = barArea,
                        bar = bar
                    )
                }
            }
        }
    ) {
        /**
         *  Typically we could draw everything here, but because of the lack of canvas.drawText
         *  APIs we have to use Android's `nativeCanvas` which seems to be drawn behind
         *  Compose's canvas.
         */
        drawIntoCanvas { canvas ->
            val (xAxisArea, yAxisArea) = axisAreas(
                drawScope = this,
                totalSize = size,
                xAxisDrawer = xAxisDrawer,
                labelDrawer = labelDrawer
            )
            val barDrawableArea = barDrawableArea(xAxisArea)

            barChartData.forEachWithArea(
                this,
                barDrawableArea,
                progress,
                labelDrawer
            ) { barArea, bar ->
                labelDrawer.drawLabel(
                    drawScope = this,
                    canvas = canvas,
                    label = bar.label,
                    barArea = barArea,
                    xAxisArea = xAxisArea
                )
            }

            yAxisDrawer.drawAxisLabels(
                drawScope = this,
                canvas = canvas,
                minValue = barChartData.minYValue,
                maxValue = barChartData.maxYValue,
                drawableArea = yAxisArea
            )
        }
    }
}