package com.justparokq.graphs

import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.design_system.AppTheme


class MainActivity : androidx.activity.ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                GraphScreen()
            }
        }
    }
}
