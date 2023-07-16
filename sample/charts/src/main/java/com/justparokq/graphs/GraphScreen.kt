package com.justparokq.graphs

import android.graphics.Point
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charts.bar_chart.BarChart
import com.example.charts.bar_chart.renderer.label.SimpleValueDrawer
import com.example.charts.line_chart.LineChart
import com.example.charts.line_chart.view_model.LineChartViewModelImpl
import com.example.charts.pie_chart.PieChart
import com.example.charts.pie_chart.PieChartData
import com.justparokq.graphs.test_data.getTestBarChartData
import com.justparokq.graphs.test_data.getTestPieChartData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class GraphType {
    PIE, BAR, LINE
}

@Composable
internal fun GraphScreen() {

    var showedChart: GraphType? by remember {
        mutableStateOf(null)
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { showedChart = GraphType.PIE }) {
                Text(text = "Плоская круговая", fontSize = 14.sp)
            }
            Button(onClick = { showedChart = GraphType.BAR }) {
                Text(text = "Гистограмма", fontSize = 14.sp)
            }
            Button(onClick = { showedChart = GraphType.LINE }) {
                Text(text = "Online line", fontSize = 14.sp)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 64.dp, horizontal = 12.dp)
        ) {
            when (showedChart) {
                GraphType.PIE -> {
                    PieChartPanel()
                }
                GraphType.BAR -> {
                    BarChartPanel()
                }
                GraphType.LINE -> {
                    LineChartPanel()
                }
                null -> {}
            }
        }
    }
}

@Composable
fun LineChartPanel() {
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

@Composable
fun BarChartPanel() {
    val barChartData = remember {
        getTestBarChartData()
    }

    BarChart(
        barChartData = barChartData,
        labelDrawer = SimpleValueDrawer(drawLocation = SimpleValueDrawer.DrawLocation.Inside)
    )
}

@Composable
fun PieChartPanel() {
    var selectedSlice: PieChartData.Slice? by remember {
        mutableStateOf(null)
    }
    val pieChartData = remember {
        getTestPieChartData()
    }

    PieChart(
        pieChartData = pieChartData,
        modifier = Modifier.fillMaxWidth(),
        selectedSlice = selectedSlice,
        onSelectedSliceChanged = { newSlice ->
            selectedSlice = if (newSlice == selectedSlice) {
                null
            } else {
                newSlice
            }
        }
    )
}