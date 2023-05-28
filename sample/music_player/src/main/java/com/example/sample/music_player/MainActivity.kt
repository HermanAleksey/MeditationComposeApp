package com.example.sample.music_player

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.design_system.AppTheme
import com.example.feature.music_player.ui.MusicScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : androidx.activity.ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicPlayerApp()
        }
    }
}

@Composable
fun MusicPlayerApp() {
    AppTheme {
        MusicScreen(
            hiltViewModel()
        )
    }
}

