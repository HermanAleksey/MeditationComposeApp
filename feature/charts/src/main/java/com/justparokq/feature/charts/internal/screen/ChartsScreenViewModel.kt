package com.justparokq.feature.charts.internal.screen

import android.graphics.Point
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justparokq.core.common.mvi.MviViewModel
import com.justparokq.feature.charts.api.chart.line.view_model.ILineChartViewModel
import com.justparokq.feature.charts.api.chart.line.view_model.LineChartViewModelImpl
import com.justparokq.feature.charts.api.chart.pie.PieChartData
import com.justparokq.feature.charts.internal.test_data.getTestBarChartData
import com.justparokq.feature.charts.internal.test_data.getTestPieChartData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class ChartType {
    Pie, Bar, Line
}

class ChartsScreenViewModel : MviViewModel<ChartsScreenState, ChartsScreenAction>,
    ILineChartViewModel by LineChartViewModelImpl(), ViewModel() {

    private val _uiState = MutableStateFlow(ChartsScreenState())
    override val uiState: StateFlow<ChartsScreenState> = combine(
        flow = lineChartViewState,
        flow2 = lineChartPoints,
        flow3 = _uiState
    ) { state, points, chartScreenState ->
        chartScreenState.copy(
            lineChartViewState = state,
            lineChartPoints = points,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = ChartsScreenState()
    )

    init {
        viewModelScope.launch {
            var x = 0
            var y = 0
            while (true) {
                addPoint(Point(x, y))
                x++
                y = (y + 1).mod(10)
                delay(300)
            }
        }
    }

    override fun processAction(action: ChartsScreenAction) {
        when (action) {
            is ChartsScreenAction.ChartButtonClick -> {
                processButtonClick(action.chart)
            }
            is ChartsScreenAction.PieChartClick -> {
                onSliceClicked(action.slice)
            }
        }
    }

    private fun processButtonClick(chart: ChartType) {
        when (chart) {
            ChartType.Pie -> _uiState.update {
                it.copy(
                    isPieChartVisible = !it.isPieChartVisible,
                    pieChartData = getTestPieChartData(),
                )
            }
            ChartType.Bar -> _uiState.update {
                it.copy(
                    isBarChartVisible = !it.isBarChartVisible,
                    barChartData = getTestBarChartData(),
                )
            }
            ChartType.Line -> _uiState.update {
                it.copy(
                    isLineChartVisible = !it.isLineChartVisible
                )
            }
        }
    }

    private fun onSliceClicked(slice: PieChartData.Slice) {
        val newSelectedSlice = if (slice == uiState.value.pieChartData.selectedSlice) {
            null
        } else {
            slice
        }
        _uiState.update {
            it.copy(
                pieChartData = it.pieChartData.copy(
                    selectedSlice = newSelectedSlice
                )
            )
        }
    }
}