package com.example.sample.music_player

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.design_system.AppTheme
import com.example.feature.music_player.data.parsers.toSong
import com.example.feature.music_player.ui.composables.music_player_widget.MusicPlayerWidget
import com.example.feature.music_player.ui.composables.music_player_widget.MusicPlaylistScreen
import com.example.feature.music_player.ui.music_player.MusicPlayerScreen
import com.example.feature.music_player.ui.viewmodels.MainViewModel
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

