package com.justparokq.graphs

import android.os.Bundle
import androidx.activity.compose.setContent
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.charts.api.screen.ChartsDemoScreen


class MainActivity : androidx.activity.ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ChartsDemoScreen()
            }
        }
    }
}
