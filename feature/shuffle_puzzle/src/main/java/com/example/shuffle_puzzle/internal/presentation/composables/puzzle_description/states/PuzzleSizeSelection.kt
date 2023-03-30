package com.example.shuffle_puzzle.internal.presentation.composables.puzzle_description.states

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerArrayResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.AppTheme
import com.example.design_system.ColorPalette
import com.example.design_system.clickableWithoutRipple
import com.example.feature.shuffle_puzzle.R

@Composable
internal fun PuzzleSizeSelection(
    puzzleSize: Int,
    updateSelectedSizeValue: (Int) -> Unit,
) {
    val sizeOptions = integerArrayResource(id = R.array.puzzle_sizes)
    val (_, onOptionSelected) = remember { mutableStateOf(sizeOptions[0]) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.select_puzzle_size),
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.W400
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            sizeOptions.forEach { size ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PuzzleRadioButton(
                        checked = (size == puzzleSize),
                        onClick = {
                            onOptionSelected(size)
                            updateSelectedSizeValue(size)
                        }
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "${size}x$size",
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.onSurface
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun PuzzleRadioButton(checked: Boolean, onClick: () -> Unit) {
    Image(
        painter = if (checked) painterResource(id = R.drawable.ic_checkbox_checked)
        else painterResource(id = R.drawable.ic_checkbox_unchecked),

        contentDescription = null,
        modifier = Modifier.clickableWithoutRipple{
            onClick()
        }
    )
}

@Preview
@Composable
internal fun PuzzleSizeSelectionPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .background(ColorPalette)
                .height(150.dp)
                .fillMaxWidth()
        ) {
            PuzzleSizeSelection(
                puzzleSize = 3,
                updateSelectedSizeValue = {}
            )
        }

    }
}