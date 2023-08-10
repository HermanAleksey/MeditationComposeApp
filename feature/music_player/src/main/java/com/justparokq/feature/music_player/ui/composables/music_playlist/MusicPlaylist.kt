package com.justparokq.feature.music_player.ui.composables.music_playlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.core.design_system.clickableWithoutRipple
import com.justparokq.feature.music_player.R
import com.justparokq.feature.music_player.data.entities.LocalRes
import com.justparokq.feature.music_player.data.entities.Song
import com.justparokq.feature.music_player.ui.composables.MediaResourceGlideImage
import com.justparokq.feature.music_player.ui.composables.music_playlist.composables.FavouriteCheckbox
import com.justparokq.feature.music_player.ui.viewmodels.MainViewState
import com.justparokq.feature.music_player.ui.viewmodels.MusicAction

@Composable
fun MusicPlaylist(
    modifier: Modifier = Modifier,
    uiState: MainViewState,
    processAction: (MusicAction) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        //todo process nullabillity
        uiState.mediaItems.data?.let {
            items(uiState.mediaItems.data) {
                MusicListItem(
                    song = it,
                    onSongClick = {
                        processAction(MusicAction.LaunchSong(it))
                    }
                )
            }
        }
    }
}

@Composable
fun MusicListItem(
    song: Song,
    onSongClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickableWithoutRipple {
                onSongClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(modifier = Modifier.fillMaxHeight()) {
            MediaResourceGlideImage(
                imageSource = song.imageSource,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(4.dp)),
            )

            Spacer(modifier = Modifier.width(18.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 4.dp),
            ) {
                Text(text = song.title, style = MaterialTheme.typography.h5)
                Text(text = song.subtitle, style = MaterialTheme.typography.h6)
            }
        }

        //todo should be property of song
        var isChecked by remember {
            mutableStateOf(false)
        }
        FavouriteCheckbox(
            size = 36.dp,
            isChecked = isChecked,
            onClick = { isChecked = !isChecked }
        )
    }
}

@Preview
@Composable
fun MusicListItemPreview() {
    AppTheme {
        MusicListItem(
            song = Song(
                mediaId = "1",
                title = "Test",
                subtitle = "subtitle",
                songSource = LocalRes(R.raw.sound_rain),
                imageSource = LocalRes(R.drawable.lovely_desert)
            ),
            onSongClick = {}
        )
    }
}