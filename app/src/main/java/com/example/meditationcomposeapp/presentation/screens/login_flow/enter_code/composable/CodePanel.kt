package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code.composable

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
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code.EnterCodeScreenState.Companion.EMPTY_NUMBER

@Composable
fun CodePanel(
    code: Array<Int>,
    onCodeDigitChanged: (Int, Int) -> Unit,
    onLastDigitFilled: () -> Unit
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

    fun doOnLastDigitFilled() {
        if (code.all { it != EMPTY_NUMBER }) {
            onLastDigitFilled()
        } else {
            setFocusToFirstNotFilledCell()
        }
    }

    LaunchedEffect(key1 = true, block = {
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
            position = 0,
            onCodeDigitChanged = onCodeDigitChanged,
            focusRequester = focusRequesters[0]!!,
            nextFocusRequester = focusRequesters[1],
            previousFocusRequester = null
        )
        CodeNumber(
            value = code[1],
            1,
            onCodeDigitChanged,
            focusRequester = focusRequesters[1]!!,
            nextFocusRequester = focusRequesters[2],
            previousFocusRequester = focusRequesters[0]
        )
        CodeNumber(
            value = code[2],
            2,
            onCodeDigitChanged,
            focusRequester = focusRequesters[2]!!,
            nextFocusRequester = focusRequesters[3],
            previousFocusRequester = focusRequesters[1]
        )
        CodeNumber(
            value = code[3],
            3,
            onCodeDigitChanged,
            focusRequester = focusRequesters[3]!!,
            nextFocusRequester = focusRequesters[4],
            previousFocusRequester = focusRequesters[2]
        )
        CodeNumber(
            value = code[4],
            4,
            onCodeDigitChanged,
            focusRequester = focusRequesters[4]!!,
            nextFocusRequester = null,
            previousFocusRequester = focusRequesters[3],
            onLastDigitFilled = ::doOnLastDigitFilled
        )
    }
}

@Composable
@Preview
fun CodePanelPreview() {
    CodePanel(
        code = arrayOf(1, 3, 2, 3, EMPTY_NUMBER),
        onCodeDigitChanged = { q, w -> }) { }
}