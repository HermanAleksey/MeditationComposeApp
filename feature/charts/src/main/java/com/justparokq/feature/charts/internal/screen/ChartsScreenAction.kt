package com.justparokq.feature.charts.internal.screen

import com.justparokq.core.common.mvi.MviAction
import com.justparokq.feature.charts.api.chart.pie.PieChartData

interface ChartsScreenAction : MviAction {

    data class ChartButtonClick(
        val chart: ChartType,
    ) : ChartsScreenAction

    data class PieChartClick(
        val slice: PieChartData.Slice,
    ) : ChartsScreenAction
}