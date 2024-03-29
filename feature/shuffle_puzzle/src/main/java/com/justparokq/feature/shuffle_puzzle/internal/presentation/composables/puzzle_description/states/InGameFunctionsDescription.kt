package com.justparokq.feature.shuffle_puzzle.internal.presentation.composables.puzzle_description.states

import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.shuffle_puzzle.R

@Composable
internal fun InGameFunctionsDescription(
    timerValueSec: Long,
    movesDone: Int,
    onRestartPuzzle: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(id = R.string.moves_done) + ": $movesDone",
            style = MaterialTheme.typography.h6.copy(
                color = MaterialTheme.colors.onSurface,
            ),
        )
        Text(
            text = stringResource(id = R.string.time_passed) + ": ${
                DateUtils.formatElapsedTime(
                    timerValueSec
                )
            }",
            style = MaterialTheme.typography.h6.copy(
                color = MaterialTheme.colors.onSurface,
            ),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.restart),
                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.onSurface,
                ),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_restart),
                contentDescription = stringResource(R.string.restart_game),
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onRestartPuzzle()
                    },
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface)
            )
        }
    }
}

@Preview
@Composable
fun InGameFunctionsDescriptionPreview() {
    AppTheme {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.White)) {
            InGameFunctionsDescription(
                timerValueSec = 123,
                movesDone = 123,
                onRestartPuzzle = {}
            )
        }
    }
}