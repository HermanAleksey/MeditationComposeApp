package com.justparokq.graphs.lib.line_chart

import android.graphics.Point
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface ILineChartViewModel {

    val lineChartData: MutableStateFlow<LineChartData>

    fun setXCapacity(capacity: Int)

    fun setYCapacity(capacity: Int)

    fun setAutoconfigureXCapacity(value: Boolean)

    fun setAutoconfigureYCapacity(value: Boolean)

    fun addPoint(point: Point)
}

data class LineChartData(
    val capacity: ChartCapacity,
    val autoCapacityX: Boolean = false,
    val autoCapacityY: Boolean = false,
    val elements: List<Point> = mutableListOf(),
) {

    data class ChartCapacity(
        val xCapacity: Int,
        val yCapacity: Int,
    )
}

class LineChartViewModelImpl : ILineChartViewModel {

    override val lineChartData: MutableStateFlow<LineChartData> = MutableStateFlow(
        LineChartData(LineChartData.ChartCapacity(INITIAL_X_CAPACITY, INITIAL_Y_CAPACITY))
    )

    override fun setXCapacity(capacity: Int) {
        lineChartData.update {
            it.copy(
                capacity = it.capacity.copy(
                    xCapacity = capacity
                )
            )
        }
    }

    override fun setYCapacity(capacity: Int) {
        lineChartData.update {
            it.copy(
                capacity = it.capacity.copy(
                    yCapacity = capacity
                )
            )
        }
    }

    override fun setAutoconfigureXCapacity(value: Boolean) {
        lineChartData.update {
            it.copy(
                autoCapacityX = value
            )
        }
    }

    override fun setAutoconfigureYCapacity(value: Boolean) {
        lineChartData.update {
            it.copy(
                autoCapacityY = value
            )
        }
    }

    override fun addPoint(point: Point) {
        lineChartData.update {
            it.copy(
                elements = it.elements + point
            )
        }
    }

    companion object {
        private const val INITIAL_X_CAPACITY = 10
        private const val INITIAL_Y_CAPACITY = 10
    }
}