package com.justparokq.graphs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.tehras.charts.bar.BarChart
import com.justparokq.graphs.lib.pie_chart.PieChart
import com.justparokq.graphs.lib.pie_chart.PieChartData
import com.justparokq.graphs.test_drawers.BarChartDataModel

@Composable
internal fun GraphScreen() {

    var selectedSlice: PieChartData.Slice? by remember {
        mutableStateOf(null)
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            val barChartDataModel = BarChartDataModel()
            BarChart(
                barChartData = barChartDataModel.barChartData,
                labelDrawer = barChartDataModel.labelDrawer
            )
        }

        PieChart(
            pieChartData = PieChartData(
                slices = listOf(
                    PieChartData.Slice(20f, Color.Red),
                    PieChartData.Slice(30f, Color.Blue),
                    PieChartData.Slice(50f, Color.Green)
                )
            ),
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
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Плоская круговая")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Гистограмма")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "С маркерами")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Динамический")
        }
    }
}