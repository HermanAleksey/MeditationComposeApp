package com.justparokq.feature.charts.internal.screen.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.justparokq.feature.charts.api.chart.pie.PieChart
import com.justparokq.feature.charts.api.chart.pie.PieChartData

@Composable
internal fun PieChartPanel(
    pieChartData: PieChartData,
    onSliceClicked: (PieChartData.Slice) -> Unit,
) {
    PieChart(
        pieChartData = pieChartData,
        modifier = Modifier.fillMaxWidth(),
        selectedSlice = pieChartData.selectedSlice,
        onSelectedSliceChanged = { newSlice ->
            onSliceClicked(newSlice)
        }
    )
}