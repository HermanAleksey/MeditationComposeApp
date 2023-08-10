package com.justparokq.feature.charts.internal.screen.composable

import android.graphics.Point
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.justparokq.feature.charts.api.chart.line.LineChart
import com.justparokq.feature.charts.api.chart.line.view_model.LineChartViewModelImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun LineChartPanel() {
    val a = rememberCoroutineScope()
    val lineChartViewModel = remember {
        LineChartViewModelImpl()
    }

    val points = lineChartViewModel.lineChartPoints.collectAsState()
    val lineChartViewState = lineChartViewModel.lineChartViewState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        a.launch {
            var x = 0
            var y = 0
            while (true) {
                lineChartViewModel.addPoint(Point(x, y))
                x++
                y = (y + 1).mod(10)
                delay(300)
            }
        }
    }

    LineChart(
        data = lineChartViewState.value,
        points = points.value,
        modifier = Modifier.fillMaxSize()
    )
}