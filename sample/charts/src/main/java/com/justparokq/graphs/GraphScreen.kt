package com.justparokq.graphs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.justparokq.graphs.test_drawers.PieChart
import com.justparokq.graphs.test_drawers.PieChartData

@Composable
internal fun GraphScreen() {

    var selectedSlice: PieChartData.Slice? by remember {
        mutableStateOf(null)
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
    ) {
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