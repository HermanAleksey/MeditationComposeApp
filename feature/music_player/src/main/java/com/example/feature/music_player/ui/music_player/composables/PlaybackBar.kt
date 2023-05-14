package com.example.musicplayer.ui.music_player.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature.music_player.extensions.toTimeString
import com.example.feature.music_player.ui.viewmodels.MusicAction
import com.example.musicplayer.ui.theme.MusicPlayerTheme

@Composable
fun PlaybackBar(
    dominantColor: Color,
    currentTimeMillis: Long,
    totalTimeMillis: Long,
    processAction: (MusicAction) -> Unit
) {
    var sliderIsChanging by remember { mutableStateOf(false) }
    var localSliderProgressValue by remember { mutableStateOf(0f) }
    val sliderProgress =
        if (sliderIsChanging) localSliderProgressValue else currentTimeMillis / totalTimeMillis.toFloat()


    val sliderColors = if (isSystemInDarkTheme()) {
        SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.onBackground,
            activeTrackColor = MaterialTheme.colors.onBackground,
            inactiveTrackColor = MaterialTheme.colors.onBackground.copy(
                alpha = ProgressIndicatorDefaults.IndicatorBackgroundOpacity
            ),
        )
    } else SliderDefaults.colors(
        thumbColor = dominantColor,
        activeTrackColor = dominantColor,
        inactiveTrackColor = dominantColor.copy(
            alpha = ProgressIndicatorDefaults.IndicatorBackgroundOpacity
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        Slider(
            value = sliderProgress,
            modifier = Modifier
                .fillMaxWidth(),
            colors = sliderColors,
            onValueChange = { progress ->
                localSliderProgressValue = progress
                sliderIsChanging = true
            },
            onValueChangeFinished = {
                processAction(
                    MusicAction.SetTime(
                        (localSliderProgressValue * totalTimeMillis).toLong()
                    )
                )
                sliderIsChanging = false
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    currentTimeMillis.toTimeString(),
                    style = MaterialTheme.typography.body2
                )
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    totalTimeMillis.toTimeString(),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun PlaybackBarPreview() {
    MusicPlayerTheme {
        PlaybackBar(
            dominantColor = Color.Cyan,
            currentTimeMillis = 100,
            totalTimeMillis = 200,
            processAction = {}
        )
    }
}