package com.example.shuffle_puzzle.presentation.puzzle_description.states

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.shuffle_puzzle.R

@Composable
fun PuzzleSizeSelection(puzzleSize: Int, updateSelectedSizeValue: (Int) -> Unit) {
    val sizeOptions = integerArrayResource(id = R.array.puzzle_sizes)
    val (_, onOptionSelected) = remember { mutableStateOf(sizeOptions[0]) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.select_puzzle_size),
            fontSize = 18.sp
        )

        Row(
            modifier = Modifier.selectableGroup(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            sizeOptions.forEach { size ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RadioButton(
                        selected = (size == puzzleSize),
                        onClick = {
                            onOptionSelected(size)
                            updateSelectedSizeValue(size)
                        },
                    )
                    Text(text = "${size}x$size")
                }
            }
        }
    }
}