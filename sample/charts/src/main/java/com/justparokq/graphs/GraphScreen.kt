package com.justparokq.graphs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.justparokq.graphs.test_drawers.PieChart
import com.justparokq.graphs.test_drawers.PieChartData

@Composable
fun GraphScreen() {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
    ) {
        PieChart(
            pieChartData = PieChartData(
                slices = listOf(
                    PieChartData.Slice(20f, Color.Red),
                    PieChartData.Slice(30f, Color.Blue, isSelected = true),
                    PieChartData.Slice(50f, Color.Green)
                )
            ),
            modifier = Modifier.fillMaxWidth()
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