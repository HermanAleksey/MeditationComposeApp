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
import com.example.feature.music_player.ui.composables.music_player.SongScreenContent
import com.example.feature.music_player.ui.composables.music_player_widget.MusicPlayerWidget
import com.example.feature.music_player.ui.composables.music_playlist.MusicPlaylist
import com.example.feature.music_player.ui.viewmodels.MusicAction
import com.example.feature.music_player.ui.viewmodels.MusicScreenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicScreen(
    musicScreenViewModel: MusicScreenViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
    ) {
        val uiState = musicScreenViewModel.uiState.collectAsState()
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
                if (!isVisible) musicScreenViewModel.processAction(MusicAction.CloseFullScreenPlayer)
            }
        }

        ModalBottomSheetLayout(
            sheetState = modalSheetState,
            sheetShape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
            sheetContent = {
                BackHandler(uiState.value.showPlayerFullScreen) {
                    musicScreenViewModel.processAction(MusicAction.CloseFullScreenPlayer)
                }

                val song = uiState.value.currentPlayingSong
                if (song != null) {
                    SongScreenContent(
                        song = song,
                        isSongPlaying = uiState.value.isSongPlaying,
                        currentTime = uiState.value.currentPlaybackPosition,
                        totalTime = uiState.value.currentSongDuration,
                        processAction = musicScreenViewModel::processAction,
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
                        processAction = musicScreenViewModel::processAction
                    )

                    MusicPlayerWidget(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(MaterialTheme.colors.secondary),
                        processAction = musicScreenViewModel::processAction,
                        currentSong = uiState.value.currentPlayingSong,
                        songIsPlaying = uiState.value.isSongPlaying
                    )
                }
            }
        }
    }
}