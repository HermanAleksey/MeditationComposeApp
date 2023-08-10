package com.justparokq.feature.charts.internal.screen.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.justparokq.feature.charts.api.chart.pie.PieChart
import com.justparokq.feature.charts.api.chart.pie.PieChartData
import com.justparokq.feature.charts.internal.test_data.getTestPieChartData

@Composable
internal fun PieChartPanel() {
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