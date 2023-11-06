package com.justparokq.feature.charts.api.chart.pie

import com.justparokq.feature.charts.LabelFormatter
import java.math.RoundingMode

private const val DEFAULT_PIE_PADDING = 60
private const val DEFAULT_PERCENTS_THRESHOLD = 8
private const val DEFAULT_SELECTED_SLICE_SCALE = 1.1f

/**
 * [showPercentsThreshold] minimal value (in percents) that has to be
 * displayed on chart
 * */
data class PieChartConfig(
    val padding: Int = DEFAULT_PIE_PADDING,
    val showPercentsThreshold: Int = DEFAULT_PERCENTS_THRESHOLD,
    val selectedSliceScale: Float = DEFAULT_SELECTED_SLICE_SCALE,
    val percentsLabelFormatter: LabelFormatter = { percents ->
        val roundedPercents = percents.toBigDecimal().setScale(1, RoundingMode.DOWN)
        "$roundedPercents%"
    },
)
