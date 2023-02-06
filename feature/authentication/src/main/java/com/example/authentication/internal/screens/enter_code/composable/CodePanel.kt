package com.example.authentication.internal.screens.enter_code.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authentication.internal.screens.enter_code.EnterCodeScreenState.Companion.EMPTY_NUMBER

@Composable
fun CodePanel(
    isEnabled: Boolean = true,
    code: Array<Int>,
    onCodeDigitChanged: (position: Int, inputtedDigit: Int) -> Boolean,
    onLastDigitFilled: () -> Unit,
) {
    val focusRequesters = mapOf(
        0 to FocusRequester(),
        1 to FocusRequester(),
        2 to FocusRequester(),
        3 to FocusRequester(),
        4 to FocusRequester(),
    )

    fun setFocusToFirstNotFilledCell() {
        val notFilledCellIndex = code.indexOfFirst { it == EMPTY_NUMBER }
        focusRequesters[notFilledCellIndex]?.requestFocus()
    }

    fun onDigitInputted(position: Int, value: Int) {
        val isCodeFullyInputted = onCodeDigitChanged(position, value)

        if (isCodeFullyInputted) {
            onLastDigitFilled()
        } else {
            setFocusToFirstNotFilledCell()
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        focusRequesters[0]?.requestFocus()
    })

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth()
    ) {
        CodeNumber(
            value = code[0],
            isEnabled = isEnabled,
            focusRequester = focusRequesters[0]!!,
            onBackspaceToPrevious = { },
            onDigitInputted = { inputtedDigit ->
                onDigitInputted(0, inputtedDigit)
                if (inputtedDigit != EMPTY_NUMBER)
                    focusRequesters[1]?.requestFocus()

            }
        )
        CodeNumber(
            value = code[1],
            isEnabled = isEnabled,
            focusRequester = focusRequesters[1]!!,
            onBackspaceToPrevious = {
                focusRequesters[0]?.requestFocus()
                onCodeDigitChanged(0, EMPTY_NUMBER)
            },
            onDigitInputted = { inputtedDigit ->
                onDigitInputted(1, inputtedDigit)
                if (inputtedDigit != EMPTY_NUMBER)
                    focusRequesters[2]?.requestFocus()

            }
        )
        CodeNumber(
            value = code[2],
            isEnabled = isEnabled,
            focusRequester = focusRequesters[2]!!,
            onBackspaceToPrevious = {
                focusRequesters[1]?.requestFocus()
                onCodeDigitChanged(1, EMPTY_NUMBER)
            },
            onDigitInputted = { inputtedDigit ->
                onDigitInputted(2, inputtedDigit)
                if (inputtedDigit != EMPTY_NUMBER)
                    focusRequesters[3]?.requestFocus()
            }
        )
        CodeNumber(
            value = code[3],
            isEnabled = isEnabled,
            focusRequester = focusRequesters[3]!!,
            onBackspaceToPrevious = {
                focusRequesters[2]?.requestFocus()
                onCodeDigitChanged(2, EMPTY_NUMBER)
            },
            onDigitInputted = { inputtedDigit ->
                onDigitInputted(3, inputtedDigit)
                if (inputtedDigit != EMPTY_NUMBER)
                    focusRequesters[4]?.requestFocus()
            }
        )
        CodeNumber(
            value = code[4],
            isEnabled = isEnabled,
            focusRequester = focusRequesters[4]!!,
            onBackspaceToPrevious = {
                focusRequesters[3]?.requestFocus()
                onCodeDigitChanged(3, EMPTY_NUMBER)
            },
            onDigitInputted = { inputtedDigit ->
                onDigitInputted(4, inputtedDigit)
            }
        )
    }
}

@Composable
@Preview
fun CodePanelPreview() {
    CodePanel(
        code = arrayOf(1, 3, 2, 3, EMPTY_NUMBER),
        onCodeDigitChanged = { q, w -> true}
    ) { }
}