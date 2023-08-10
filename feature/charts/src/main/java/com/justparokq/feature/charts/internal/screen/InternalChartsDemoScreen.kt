package com.justparokq.feature.charts.internal.screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.justparokq.feature.charts.internal.screen.composable.BarChartPanel
import com.justparokq.feature.charts.internal.screen.composable.LineChartPanel
import com.justparokq.feature.charts.internal.screen.composable.PieChartPanel

internal enum class GraphType {
    PIE, BAR, LINE
}

@Composable
internal fun InternalChartsDemoScreen() {
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