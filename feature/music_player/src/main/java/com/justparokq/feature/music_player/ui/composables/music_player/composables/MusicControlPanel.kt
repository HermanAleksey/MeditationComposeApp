package com.justparokq.feature.music_player.ui.composables.music_player.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.music_player.R
import com.justparokq.feature.music_player.data.entities.Song
import com.justparokq.feature.music_player.data.entities.WebURL
import com.justparokq.feature.music_player.ui.viewmodels.MusicAction

@OptIn(ExperimentalAnimationGraphicsApi::class)
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
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    processAction(MusicAction.PreviousTrack)
                }
                .size(32.dp),
        )
        Icon(
            imageVector = Icons.Rounded.Replay10,
            contentDescription = "Replay 10 seconds",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    processAction(MusicAction.Rewind)
                }
                .size(32.dp)
        )

        var buttonBoxSize by remember { mutableStateOf(58.dp) }
        val animatedSize = animateDpAsState(
            targetValue = buttonBoxSize,
            animationSpec = spring(stiffness = 50f),
            label = "",
        )
        Box(
            modifier = Modifier
                .size(animatedSize.value)
                .clip(CircleShape)
                .background(MaterialTheme.colors.onBackground)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            buttonBoxSize = 72.dp
                            processAction(MusicAction.ToggleSongPlayback(song))
                            tryAwaitRelease()
                            buttonBoxSize = 58.dp
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = rememberAnimatedVectorPainter(
                    AnimatedImageVector.animatedVectorResource(R.drawable.avd_anim),
                    isSongPlaying
                ),
                contentDescription = "Play",
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
            )
        }
        Icon(
            imageVector = Icons.Rounded.Forward10,
            contentDescription = "Forward 10 seconds",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    processAction(MusicAction.FastForward)

                }
                .size(32.dp)
        )
        Icon(
            imageVector = Icons.Rounded.SkipNext,
            contentDescription = "Skip Next",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    processAction(MusicAction.NextTrack)
                }
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
                songSource = WebURL(""),
                imageSource = WebURL(""),
            ),
            isSongPlaying = true,
            processAction = {}
        )
    }
}