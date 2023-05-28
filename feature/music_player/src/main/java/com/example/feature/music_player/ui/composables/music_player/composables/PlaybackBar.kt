package com.example.feature.music_player.ui.composables.music_player.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.AppTheme
import com.example.feature.music_player.extensions.toTimeString
import com.example.feature.music_player.ui.viewmodels.MusicAction

@Composable
fun PlaybackBar(
    modifier: Modifier = Modifier,
    currentTimeMillis: Long,
    totalTimeMillis: Long,
    processAction: (MusicAction) -> Unit,
) {
    var sliderIsChanging by remember { mutableStateOf(false) }
    var localSliderProgressValue by remember { mutableStateOf(0f) }
    val sliderProgress =
        if (sliderIsChanging) localSliderProgressValue else currentTimeMillis / totalTimeMillis.toFloat()


    val sliderColors = SliderDefaults.colors(
        thumbColor = MaterialTheme.colors.onBackground,
        activeTrackColor = MaterialTheme.colors.onBackground,
        inactiveTrackColor = MaterialTheme.colors.onBackground.copy(
            alpha = ProgressIndicatorDefaults.IndicatorBackgroundOpacity
        ),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                currentTimeMillis.toTimeString(),
                style = MaterialTheme.typography.body1
            )
            Text(
                totalTimeMillis.toTimeString(),
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview
@Composable
fun PlaybackBarPreview() {
    AppTheme {
        PlaybackBar(
            currentTimeMillis = 100,
            totalTimeMillis = 200,
            processAction = {}
        )
    }
}