package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CodePanel(
    code: Array<Int>,
    onCodeDigitChanged: (Int, Int) -> Unit,
    onLastDigitFilled: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val firstDigitFocusRequester = FocusRequester()
    val secondDigitFocusRequester = FocusRequester()
    val thirdDigitFocusRequester = FocusRequester()
    val fourthDigitFocusRequester = FocusRequester()
    val fivesDigitFocusRequester = FocusRequester()

    LaunchedEffect(key1 = true, block = {
        firstDigitFocusRequester.requestFocus()
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
            focusManager = focusManager,
            focusRequester = firstDigitFocusRequester,
            nextFocusRequester = secondDigitFocusRequester,
            previousFocusRequester = null
        )
        CodeNumber(
            value = code[1],
            1,
            onCodeDigitChanged,
            focusManager = focusManager,
            focusRequester = secondDigitFocusRequester,
            nextFocusRequester = thirdDigitFocusRequester,
            previousFocusRequester = firstDigitFocusRequester
        )
        CodeNumber(
            value = code[2],
            2,
            onCodeDigitChanged,
            focusManager = focusManager,
            focusRequester = thirdDigitFocusRequester,
            nextFocusRequester = fourthDigitFocusRequester,
            previousFocusRequester = secondDigitFocusRequester
        )
        CodeNumber(
            value = code[3],
            3,
            onCodeDigitChanged,
            focusManager = focusManager,
            focusRequester = fourthDigitFocusRequester,
            nextFocusRequester = fivesDigitFocusRequester,
            previousFocusRequester = thirdDigitFocusRequester
        )
        CodeNumber(
            value = code[4],
            4,
            onCodeDigitChanged,
            focusManager = focusManager,
            focusRequester = fivesDigitFocusRequester,
            nextFocusRequester = null,
            previousFocusRequester = fourthDigitFocusRequester,
            onLastDigitFilled = onLastDigitFilled
        )
    }
}

@Composable
@Preview
fun CodePanelPreview() {
    CodePanel(code = arrayOf(1, 3, 2, 3, -1), onCodeDigitChanged = { q, w -> }) {

    }
}