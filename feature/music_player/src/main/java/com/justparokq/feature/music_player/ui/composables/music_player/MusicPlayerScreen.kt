package com.justparokq.feature.music_player.ui.composables.music_player

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.justparokq.feature.music_player.data.entities.Song
import com.justparokq.feature.music_player.ui.composables.music_player.composables.MusicControlPanel
import com.justparokq.feature.music_player.ui.composables.music_player.composables.PlaybackBar
import com.justparokq.feature.music_player.ui.composables.music_player.composables.SpinDiskAnimation
import com.justparokq.feature.music_player.ui.viewmodels.MusicAction


@Composable
fun SongScreenContent(
    song: Song,
    isSongPlaying: Boolean,
    currentTime: Long,
    totalTime: Long,
    processAction: (MusicAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colors.secondary)
    ) {
        IconButton(
            onClick = {
                processAction(MusicAction.CloseFullScreenPlayer)
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Image(
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = "Close fullscreen player",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            SpinDiskAnimation(
                imageSource = song.imageSource,
                isSpinning = isSongPlaying
            )

            Text(
                text = song.title,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                song.subtitle,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.alpha(0.6f)
            )

            PlaybackBar(
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