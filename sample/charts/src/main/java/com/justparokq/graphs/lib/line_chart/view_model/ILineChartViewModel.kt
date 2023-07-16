package com.justparokq.graphs.lib.line_chart.view_model

import android.graphics.Point
import kotlinx.coroutines.flow.MutableStateFlow

interface ILineChartViewModel {

   val lineChartViewState: MutableStateFlow<LineChartViewState>

    val lineChartPoints: MutableStateFlow<List<Point>>

    fun setXCapacity(capacity: Int)

    fun setYCapacity(capacity: Int)

    fun setAutoconfigureXCapacity(value: Boolean)

    fun setAutoconfigureYCapacity(value: Boolean)

    fun addPoint(point: Point)
}