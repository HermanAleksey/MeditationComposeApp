package com.example.feature.music_player.ui.composables.music_player_widget


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.feature.music_player.R
import com.example.feature.music_player.data.entities.MediaDataSource
import com.example.feature.music_player.data.entities.Song
import com.example.feature.music_player.ui.viewmodels.MusicAction
import com.example.musicplayer.ui.theme.MusicPlayerTheme
import com.google.accompanist.coil.rememberCoilPainter
import kotlin.math.roundToInt

private const val DRAG_DETECTION_OFFSET = 100f

@Composable
fun MusicPlayerWidget(
    modifier: Modifier = Modifier,
    currentSong: Song?,
    songIsPlaying: Boolean,
    processAction: (MusicAction) -> Unit,
) {
    var offsetX by remember { mutableStateOf(0) }

    AnimatedVisibility(
        visible = currentSong != null,
        modifier = modifier
    ) {
        if (currentSong != null) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetX, 0) }
                    .fillMaxWidth()
                    //todo Можно переделать с Pagination. Будет плавнее, красивее и виден некст трэк. Пока оставить так.
                    //можно ориентироваться на Споти. Там ездит только название трэка.
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                when {
                                    offsetX > DRAG_DETECTION_OFFSET -> {
                                        processAction(MusicAction.PreviousTrack)
                                    }
                                    offsetX < -DRAG_DETECTION_OFFSET -> {
                                        processAction(MusicAction.NextTrack)
                                    }
                                }
                                offsetX = 0
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount.x.roundToInt()
                            }
                        )
                    },
            ) {
                MusicPlayerWidgetContent(
                    song = currentSong,
                    isPlaying = songIsPlaying,
                    showPlayerFullScreen = { open ->
                        if (open) processAction(MusicAction.OpenFullScreenPlayer)
                        else processAction(MusicAction.CloseFullScreenPlayer)

                    },
                    toggleSongPlayback = { song ->
                        processAction(MusicAction.ToggleSongPlayback(song))
                    }
                )
            }
        }
    }
}


@Composable
private fun MusicPlayerWidgetContent(
    song: Song,
    isPlaying: Boolean,
    showPlayerFullScreen: (Boolean) -> Unit,
    toggleSongPlayback: (Song) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable(
                onClick = {
                    showPlayerFullScreen(true)
                }
            )
    ) {
        val albumImagePainter = when (song.imageSource) {
            is MediaDataSource.WebSource -> {
                rememberCoilPainter(song.imageSource.url)
            }
            is MediaDataSource.LocalSource -> {
                painterResource(id = song.imageSource.resId)
            }
        }
        Image(
            painter = albumImagePainter,
            contentDescription = song.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .offset(16.dp)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(vertical = 8.dp, horizontal = 32.dp),
        ) {
            Text(
                song.title,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                song.subtitle,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .alpha(0.60f)
            )
        }

        Image(
            painter = rememberCoilPainter(
                request = if (isPlaying) {
                    R.drawable.ic_round_pause
                } else {
                    R.drawable.ic_round_play_arrow
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(48.dp)
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = rememberRipple(
                        bounded = false,
                        radius = 24.dp
                    ),
                    onClick = {
                        toggleSongPlayback(song)
                    }
                )
        )
    }
}

@Preview
@Composable
fun MusicPlayerWidgetPreview() {
    MusicPlayerTheme {
        MusicPlayerWidget(
            modifier = Modifier.fillMaxWidth(),
            currentSong = Song(
                mediaId = "11",
                title = "Title",
                subtitle = "Subtitle",
                songSource = MediaDataSource.WebSource(""),
                imageSource = MediaDataSource.WebSource(""),
            ),
            songIsPlaying = true,
            processAction = {}
        )
    }
}