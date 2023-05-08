package com.example.sample.music_player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.design_system.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MusicPlayerApp()
        }
    }
}

//@ExperimentalMaterialApi
@Composable
fun MusicPlayerApp() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
//            val mainViewModel: MainViewModel = hiltViewModel()
//            val uiState = mainViewModel.uiState.collectAsState()
//
//            MusicPlaylistScreen(
//                uiState = uiState.value,
//                processAction = mainViewModel::processAction
//            )
//
//            MusicPlayerWidget(
//                modifier = Modifier
//                    .align(Alignment.BottomCenter),
//                processAction = mainViewModel::processAction,
//                currentSong = uiState.value.currentPlayingSong?.toSong(),
//                songIsPlaying = uiState.value.isSongPlaying
//            )
//
//            MusicPlayerScreen(
//                uiState = uiState.value,
//                processAction = mainViewModel::processAction,
//            )
        }
    }
}

