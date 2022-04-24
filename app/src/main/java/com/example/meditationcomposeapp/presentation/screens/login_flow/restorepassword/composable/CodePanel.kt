package com.example.meditationcomposeapp.presentation.screens.login_flow.restorepassword.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun CodePanel(
    code: Array<Int>,
    setDigit: (Int, Int) -> Unit,
    onLastDigitFilled: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val firstDigitFocusRequester = FocusRequester()
    val secondDigitFocusRequester = FocusRequester()
    val thirdDigitFocusRequester = FocusRequester()
    val fourthDigitFocusRequester = FocusRequester()
    val fivesDigitFocusRequester = FocusRequester()

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth()
    ) {

        CodeNumber(
            value = code[0],
            position = 0,
            setDigit = setDigit,
            focusManager = focusManager,
            focusRequester = firstDigitFocusRequester,
            nextFocusRequester = secondDigitFocusRequester,
            previousFocusRequester = null
        )
        CodeNumber(
            value = code[1],
            1,
            setDigit,
            focusManager = focusManager,
            focusRequester = secondDigitFocusRequester,
            nextFocusRequester = thirdDigitFocusRequester,
            previousFocusRequester = firstDigitFocusRequester
        )
        CodeNumber(
            value = code[2],
            2,
            setDigit,
            focusManager = focusManager,
            focusRequester = thirdDigitFocusRequester,
            nextFocusRequester = fourthDigitFocusRequester,
            previousFocusRequester = secondDigitFocusRequester
        )
        CodeNumber(
            value = code[3],
            3,
            setDigit,
            focusManager = focusManager,
            focusRequester = fourthDigitFocusRequester,
            nextFocusRequester = fivesDigitFocusRequester,
            previousFocusRequester = thirdDigitFocusRequester
        )
        CodeNumber(
            value = code[4],
            4,
            setDigit,
            focusManager = focusManager,
            focusRequester = fivesDigitFocusRequester,
            nextFocusRequester = null,
            previousFocusRequester = fourthDigitFocusRequester,
            onLastDigitFilled = onLastDigitFilled
        )
    }
}