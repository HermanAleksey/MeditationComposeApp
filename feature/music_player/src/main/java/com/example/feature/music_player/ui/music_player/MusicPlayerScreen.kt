package com.example.feature.music_player.ui.music_player

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.feature.music_player.data.entities.MediaDataSource
import com.example.feature.music_player.data.entities.Song
import com.example.feature.music_player.data.parsers.toSong
import com.example.feature.music_player.ui.music_player.composables.MusicControlPanel
import com.example.feature.music_player.ui.music_player.composables.PlaybackBar
import com.example.feature.music_player.ui.music_player.composables.SpinDiskAnimation
import com.example.feature.music_player.ui.viewmodels.MainViewState
import com.example.feature.music_player.ui.viewmodels.MusicAction

@ExperimentalMaterialApi
@Composable
fun MusicPlayerScreen(
    uiState: MainViewState,
    processAction: (MusicAction) -> Unit,
) {
    val song = uiState.currentPlayingSong
    BackHandler {
        processAction(MusicAction.CloseFullScreenPlayer)
    }

    AnimatedVisibility(
        visible = song != null && uiState.showPlayerFullScreen,
        enter = slideInVertically(
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            targetOffsetY = { it }
        )) {
        if (song != null) {
            SongScreenContent(
                song = song.toSong()!!,
                processAction = processAction,
                uiState = uiState
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun SongScreenContent(
    song: Song,
    processAction: (MusicAction) -> Unit,
    uiState: MainViewState,
) {
    val backgroundColor = MaterialTheme.colors.background

    val dominantColor by remember {
        mutableStateOf(backgroundColor)
    }

    val request = when (song.imageSource) {
        is MediaDataSource.WebSource -> {
            ImageRequest.Builder(LocalContext.current)
                .data(song.imageSource.url)
                .build()
        }
        is MediaDataSource.LocalSource -> {
            ImageRequest.Builder(LocalContext.current)
                .data(song.imageSource.resId)
                .build()
        }
    }

    val imagePainter = rememberAsyncImagePainter(
        model = request,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SongScreenContentContent(
            song = song,
            isSongPlaying = uiState.isSongPlaying,
            imagePainter = imagePainter,
            dominantColor = dominantColor,
            currentTime = uiState.currentPlaybackPosition,
            totalTime = uiState.currentSongDuration,
            processAction = processAction,
        )
    }
}

@Composable
private fun SongScreenContentContent(
    song: Song,
    isSongPlaying: Boolean,
    imagePainter: Painter,
    dominantColor: Color,
    currentTime: Long,
    totalTime: Long,
    processAction: (MusicAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column {
            IconButton(
                onClick = {
                    processAction(MusicAction.CloseFullScreenPlayer)
                }
            ) {
                Image(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = "Close fullscreen player",
                    colorFilter = ColorFilter.tint(LocalContentColor.current)
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                SpinDiskAnimation(
                    albumPainter = imagePainter,
                    isSpinning = isSongPlaying
                )

                Text(
                    text = song.title,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    song.subtitle,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.alpha(0.6f)
                )

                PlaybackBar(
                    dominantColor = dominantColor,
                    currentTimeMillis = currentTime,
                    totalTimeMillis = totalTime,
                    processAction = processAction
                )

                MusicControlPanel(
                    isSongPlaying = isSongPlaying,
                    song = song,
                    processAction = processAction
                )
            }
        }
    }
}


