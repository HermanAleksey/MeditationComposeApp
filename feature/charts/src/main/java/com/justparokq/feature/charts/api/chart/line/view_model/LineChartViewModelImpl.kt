package com.justparokq.feature.charts.api.chart.line.view_model

import android.graphics.Point
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class LineChartViewModelImpl : ILineChartViewModel {

    override val lineChartViewState: MutableStateFlow<LineChartViewState> =
        MutableStateFlow(
            LineChartViewState(
                viewPort = LineChartViewState.ChartViewPort(
                    INITIAL_X_CAPACITY,
                    INITIAL_Y_CAPACITY
                ),
            )
        )

    override val lineChartPoints: MutableStateFlow<List<Point>> = MutableStateFlow(
        listOf()
    )

    override fun setXCapacity(capacity: Int) {
        lineChartViewState.update {
            it.copy(
                viewPort = it.viewPort.copy(
                    xCapacity = capacity
                )
            )
        }
    }

    override fun setYCapacity(capacity: Int) {
        lineChartViewState.update {
            it.copy(
                viewPort = it.viewPort.copy(
                    yCapacity = capacity
                )
            )
        }
    }

    override fun setAutoconfigureXCapacity(value: Boolean) {
        lineChartViewState.update {
            it.copy(
                autoCapacityX = value
            )
        }
    }

    override fun setAutoconfigureYCapacity(value: Boolean) {
        lineChartViewState.update {
            it.copy(
                autoCapacityY = value
            )
        }
    }

    override fun addPoint(point: Point) {
        lineChartPoints.update {
            it + point
        }
    }

    companion object {
        private const val INITIAL_X_CAPACITY = 10
        private const val INITIAL_Y_CAPACITY = 10
    }
}