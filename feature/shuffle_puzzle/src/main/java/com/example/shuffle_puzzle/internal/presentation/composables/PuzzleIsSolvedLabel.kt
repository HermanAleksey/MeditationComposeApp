package com.example.shuffle_puzzle.internal.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun PuzzleIsSolvedLabel() {
    Card(
        shape = RoundedCornerShape(2.dp),//todo fix dimensionResource(id =  R.dimen.radius_pop_up_corner)),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "solved",//todo fix stringResource(id = R.string.puzzle_solved),
                style = MaterialTheme.typography.body1.copy(color = Color.Black),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 60.dp, vertical = 25.dp)
            )
        }
    }
}