package com.example.feature.music_player.ui.music_player.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Forward10
import androidx.compose.material.icons.rounded.Replay10
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.AppTheme
import com.example.feature.music_player.R
import com.example.feature.music_player.data.entities.MediaDataSource
import com.example.feature.music_player.data.entities.Song
import com.example.feature.music_player.ui.viewmodels.MusicAction

@Composable
internal fun MusicControlPanel(
    isSongPlaying: Boolean,
    song: Song,
    processAction: (MusicAction) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Icon(
            imageVector = Icons.Rounded.SkipPrevious,
            contentDescription = "Skip Previous",
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = {
                    processAction(MusicAction.PreviousTrack)
                })
                .padding(12.dp)
                .size(32.dp)
        )
        Icon(
            imageVector = Icons.Rounded.Replay10,
            contentDescription = "Replay 10 seconds",
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = {
                    processAction(MusicAction.Rewind)
                })
                .padding(12.dp)
                .size(32.dp)
        )
        Icon(
            painter = painterResource(
                if (isSongPlaying) R.drawable.ic_round_pause
                else R.drawable.ic_round_play_arrow
            ),
            contentDescription = "Play",
            tint = MaterialTheme.colors.background,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colors.onBackground)
                .clickable(onClick = {
                    processAction(MusicAction.ToggleSongPlayback(song))
                })
                .size(64.dp)
                .padding(8.dp)
        )
        Icon(
            imageVector = Icons.Rounded.Forward10,
            contentDescription = "Forward 10 seconds",
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = {
                    processAction(MusicAction.FastForward)

                })
                .padding(12.dp)
                .size(32.dp)
        )
        Icon(
            imageVector = Icons.Rounded.SkipNext,
            contentDescription = "Skip Next",
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = {
                    processAction(MusicAction.NextTrack)
                })
                .padding(12.dp)
                .size(32.dp)
        )
    }
}

@Preview
@Composable
fun MusicControlPanelPreview() {
    AppTheme {
        MusicControlPanel(
            song = Song(
                mediaId = "11",
                title = "Title",
                subtitle = "Subtitle",
                songSource = MediaDataSource.WebSource(""),
                imageSource = MediaDataSource.WebSource(""),
            ),
            isSongPlaying = true,
            processAction = {}
        )
    }
}