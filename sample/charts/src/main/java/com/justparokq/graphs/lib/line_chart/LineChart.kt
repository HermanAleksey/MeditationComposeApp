package com.justparokq.graphs.lib.line_chart

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class LineChartConfig(
    val offset: Int,
)

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: LineChartData,
    config: LineChartConfig,
) {
    Canvas(
        modifier = modifier,
        onDraw = {
            this.size.height
            this.size.width

            //draw axis
            drawLine(
                start = Offset(
                    config.offset.toFloat(),
                    size.height - config.offset.toFloat()
                ),
                end = Offset(config.offset.toFloat(), config.offset.toFloat()),
                color = Color.Black,
                strokeWidth = 10f
            )
            drawLine(
                start = Offset(
                    size.width - config.offset.toFloat(),
                    size.height - config.offset.toFloat()
                ),
                end = Offset(config.offset.toFloat(), size.height - config.offset.toFloat()),
                color = Color.Black,
                strokeWidth = 10f
            )
            //draw labels
            //draw lines
        }
    )
}