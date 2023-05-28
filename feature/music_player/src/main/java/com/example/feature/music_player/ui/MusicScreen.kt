package com.example.feature.music_player.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feature.music_player.data.parsers.toSong
import com.example.feature.music_player.ui.composables.music_player.SongScreenContent
import com.example.feature.music_player.ui.composables.music_player_widget.MusicPlayerWidget
import com.example.feature.music_player.ui.composables.music_playlist.MusicPlaylist
import com.example.feature.music_player.ui.viewmodels.MainViewModel
import com.example.feature.music_player.ui.viewmodels.MusicAction
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicScreen(
    mainViewModel: MainViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
    ) {
        val uiState = mainViewModel.uiState.collectAsState()
        val coroutineScope = rememberCoroutineScope()
        val modalSheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = {
                it != ModalBottomSheetValue.HalfExpanded
            },
            skipHalfExpanded = true,
            animationSpec = SpringSpec(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow,
                visibilityThreshold = Spring.DefaultDisplacementThreshold
            )
        )

        LaunchedEffect(key1 = uiState.value.showPlayerFullScreen) {
            coroutineScope.launch {
                if (uiState.value.showPlayerFullScreen)
                    modalSheetState.show()
                else modalSheetState.hide()
            }
        }
        LaunchedEffect(modalSheetState) {
            snapshotFlow { modalSheetState.isVisible }.collect { isVisible ->
                if (!isVisible) mainViewModel.processAction(MusicAction.CloseFullScreenPlayer)
            }
        }

        ModalBottomSheetLayout(
            sheetState = modalSheetState,
            sheetShape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
            sheetContent = {
                BackHandler {
                    mainViewModel.processAction(MusicAction.CloseFullScreenPlayer)
                }

                val song = uiState.value.currentPlayingSong?.toSong()
                if (song != null) {
                    SongScreenContent(
                        song = song,
                        isSongPlaying = uiState.value.isSongPlaying,
                        currentTime = uiState.value.currentPlaybackPosition,
                        totalTime = uiState.value.currentSongDuration,
                        processAction = mainViewModel::processAction,
                    )
                }
            }
        ) {
            Scaffold { paddings ->
                Box(modifier = Modifier.padding(paddings)) {
                    MusicPlaylist(
                        modifier = Modifier
                            .padding(bottom = 60.dp)
                            .fillMaxSize(),
                        uiState = uiState.value,
                        processAction = mainViewModel::processAction
                    )

                    MusicPlayerWidget(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(MaterialTheme.colors.secondary),
                        processAction = mainViewModel::processAction,
                        currentSong = uiState.value.currentPlayingSong?.toSong(),
                        songIsPlaying = uiState.value.isSongPlaying
                    )
                }
            }
        }
    }
}