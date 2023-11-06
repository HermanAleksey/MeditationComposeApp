package com.justparokq.feature.charts.internal.screen

import android.graphics.Point
import com.justparokq.core.common.mvi.MviState
import com.justparokq.feature.charts.api.chart.bar.BarChartData
import com.justparokq.feature.charts.api.chart.line.view_model.LineChartViewState
import com.justparokq.feature.charts.api.chart.pie.PieChartData
import com.justparokq.feature.charts.internal.test_data.getTestBarChartData
import com.justparokq.feature.charts.internal.test_data.getTestPieChartData

data class ChartsScreenState(
    val isBarChartVisible: Boolean = false,
    val isPieChartVisible: Boolean = false,
    val isLineChartVisible: Boolean = false,
    val barChartData: BarChartData = getTestBarChartData(),
    val pieChartData: PieChartData = getTestPieChartData(),
    val lineChartViewState: LineChartViewState? = null,
    val lineChartPoints: List<Point>? = null,
) : MviState {

    val isLineChartDataAvailable = lineChartPoints != null && lineChartViewState != null
}
