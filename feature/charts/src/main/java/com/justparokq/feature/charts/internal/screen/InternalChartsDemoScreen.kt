package com.justparokq.feature.charts.internal.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.common_composables.ColorBackground
import com.justparokq.feature.charts.internal.screen.composable.BarChartPanel
import com.justparokq.feature.charts.internal.screen.composable.LineChartPanel
import com.justparokq.feature.charts.internal.screen.composable.PieChartPanel
import com.justparokq.feature.media_player.R

@Composable
internal fun InternalChartsDemoScreen(
    uiState: State<ChartsScreenState>,
    processAction: (ChartsScreenAction) -> Unit,
) {
    ColorBackground(color = MaterialTheme.colors.background) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item {
                    ChartToggleButton(
                        isVisible = uiState.value.isPieChartVisible,
                        title = stringResource(R.string.pie_chart_button),
                        onButtonClick = {
                            processAction(
                                ChartsScreenAction.ChartButtonClick(ChartType.Pie)
                            )
                        },
                        chart = {
                            PieChartPanel(
                                pieChartData = uiState.value.pieChartData,
                                onSliceClicked = { newSlice ->
                                    processAction(
                                        ChartsScreenAction.PieChartClick(
                                            slice = newSlice
                                        )
                                    )
                                }
                            )
                        }
                    )
                }
                item { Spacer(modifier = Modifier.height(12.dp)) }
                item {
                    ChartToggleButton(
                        isVisible = uiState.value.isBarChartVisible,
                        title = stringResource(R.string.bar_chart_button),
                        onButtonClick = {
                            processAction(
                                ChartsScreenAction.ChartButtonClick(ChartType.Bar)
                            )
                        },
                        chart = { BarChartPanel(uiState.value.barChartData) }
                    )
                }
                item { Spacer(modifier = Modifier.height(12.dp)) }
                item {
                    if (uiState.value.isLineChartDataAvailable)
                        ChartToggleButton(
                            isVisible = uiState.value.isLineChartVisible,
                            title = stringResource(R.string.line_chart_button),
                            onButtonClick = {
                                processAction(
                                    ChartsScreenAction.ChartButtonClick(ChartType.Line)
                                )
                            },
                            chart = {
                                LineChartPanel(
                                    points = uiState.value.lineChartPoints!!,
                                    state = uiState.value.lineChartViewState!!,
                                )
                            }
                        )
                }
                item { Spacer(modifier = Modifier.height(24.dp)) }
            }
        }
    }
}

@Composable
internal fun ChartToggleButton(
    isVisible: Boolean,
    title: String,
    onButtonClick: () -> Unit,
    chart: @Composable () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        onClick = { onButtonClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(text = title, style = MaterialTheme.typography.h5)
    }
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(24.dp)
        ) {
            chart()
        }
    }
}