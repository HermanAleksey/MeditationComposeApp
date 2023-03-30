package com.example.shuffle_puzzle.internal.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.AppTheme
import com.example.feature.shuffle_puzzle.R

@Composable
internal fun PuzzleIsSolvedDialog(
    movesDone: Int,
    onResetClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 33.dp, vertical = 7.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(7.dp))
            Image(
                painter = painterResource(id = R.drawable.puzzle_solved_dialog_illustration),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.congratulations_text),
                style = MaterialTheme.typography.body1.copy(color = Color.Black),
                textAlign = TextAlign.Center,
            )
            Text(
                text = "${stringResource(id = R.string.moves_done)}: $movesDone",
                style = MaterialTheme.typography.body2.copy(color = Color.Black),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(MaterialTheme.colors.background)
                    .clickable {
                        onResetClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_restart),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Preview
@Composable
internal fun PuzzleIsSolvedDialogPreview() {
    AppTheme {
        PuzzleIsSolvedDialog(5) {}
    }
}