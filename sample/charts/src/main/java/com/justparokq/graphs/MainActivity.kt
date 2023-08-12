package com.justparokq.graphs

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.charts.api.screen.ChartsDemoScreen
import com.justparokq.feature.charts.internal.screen.ChartsScreenViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : androidx.activity.ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                 val exampleViewModel: ChartsScreenViewModel by viewModels()
                ChartsDemoScreen(exampleViewModel)
            }
        }
    }
}
