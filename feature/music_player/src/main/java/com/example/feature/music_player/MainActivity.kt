package com.example.feature.music_player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.design_system.AppTheme
import com.example.feature.music_player.data.parsers.toSong
import com.example.feature.music_player.ui.composables.music_player_widget.MusicPlayerWidget
import com.example.feature.music_player.ui.composables.music_player_widget.MusicPlaylistScreen
import com.example.feature.music_player.ui.music_player.MusicPlayerScreen
import com.example.feature.music_player.ui.viewmodels.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MusicPlayerApp()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MusicPlayerApp() {
    val systemUiController = rememberSystemUiController()

    val useDarkIcons = !isSystemInDarkTheme()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
    }
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val mainViewModel: MainViewModel = hiltViewModel()
            val uiState = mainViewModel.uiState.collectAsState()

            MusicPlaylistScreen(
                uiState = uiState.value,
                processAction = mainViewModel::processAction
            )

            MusicPlayerWidget(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                processAction = mainViewModel::processAction,
                currentSong = uiState.value.currentPlayingSong?.toSong(),
                songIsPlaying = uiState.value.isSongPlaying
            )

            MusicPlayerScreen(
                uiState = uiState.value,
                processAction = mainViewModel::processAction,
            )
        }
    }
}

