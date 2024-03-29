package com.justparokq.feature.authentication.internal.screens.enter_code.composable

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.justparokq.core.common.utils.emptyString
import com.justparokq.core.design_system.AppTheme
import com.justparokq.core.design_system.Montserrat
import com.justparokq.feature.authentication.api.enter_code_screen.EnterCodeScreenState.Companion.EMPTY_NUMBER

/**
 * Represent 1 number from code panel
 *
 * Fields:
 * [value] contains current number, written in cell;
 * if value is empty - set's value to -1
 * */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun CodeNumber(
    value: Int,
    isEnabled: Boolean,
    focusRequester: FocusRequester,
    onBackspaceToPrevious: () -> Unit,
    onDigitInputted: (Int) -> Unit,
) {

    fun onValueChanged(newValue: String) {
        //when input is cleared or value is not digit - set emmit [-1]
        if (newValue.isEmpty() || !newValue.isDigitsOnly()) {
            onDigitInputted(EMPTY_NUMBER)
            return
        }

        val inputtedDigit = newValue.toInt() % 10
        onDigitInputted(inputtedDigit)
    }

    TextField(
        value = value.let {
            if (it == EMPTY_NUMBER) emptyString() else it.toString()
        },
        enabled = isEnabled,
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 36.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.W400,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = MaterialTheme.colors.onBackground
        ),
        onValueChange = { onValueChanged(it) },
        modifier = Modifier
            .width(55.dp)
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.key == Key.Backspace && value == EMPTY_NUMBER) {
                    onBackspaceToPrevious()
                    true
                } else false
            }
    )
}

@Composable
@Preview
private fun CodeNumberPreview() {
    AppTheme {
        CodeNumber(
            value = 3,
            isEnabled = true,
            focusRequester = FocusRequester(),
            onBackspaceToPrevious = {},
            onDigitInputted = {}
        )
    }
}