package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code.composable

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.meditationcomposeapp.ui.theme.Alegreya
import com.example.meditationcomposeapp.ui.theme.ColorBackground
import com.example.meditationcomposeapp.ui.theme.ColorTextHint

/**
 * Represent 1 number from code panel
 *
 * Fields:
 * [value] contains current number, written in cell;
 * if value is empty - set's value to -1
 * [position] contains ordered position of cell;
 * used for setting value of cell to map
 * [onCodeDigitChanged] method from @CodePanel that set's value of cell
 * [focusManager] allows to work with focus, change it
 * [focusRequester] used to be able to focus on current view
 * [nextFocusRequester] used to change focus to next cell
 * [previousFocusRequester] used to change focus to previous cell
 * */

@Composable
fun CodeNumber(
    value: Int,
    position: Int,
    onCodeDigitChanged: (Int, Int) -> Unit,
    focusManager: FocusManager,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester? = null,
    previousFocusRequester: FocusRequester? = null,
    onLastDigitFilled: (() -> Unit)? = null
) {

    fun moveFocusToNextDigit() {
        if (nextFocusRequester != null)
            nextFocusRequester.requestFocus()
        else {
            onLastDigitFilled?.invoke()
            focusManager.clearFocus()
        }
    }

    fun moveFocusToPreviousDigit() {
        previousFocusRequester?.requestFocus()
    }

    fun processInputtedNumber(newValue: String) {
        val inputDigit = newValue.toInt() % 10
        onCodeDigitChanged(position, inputDigit)
        moveFocusToNextDigit()
    }

    fun onValueChanged(newValue: String){
        //when input is cleared or value is not digit - set emmit [-1]
        if (newValue.isEmpty() || !newValue.isDigitsOnly()) {
            onCodeDigitChanged(position, -1)
            return
        }
        processInputtedNumber(newValue)
    }

    TextField(
        value = value.let {
            if (it == -1) "" else it.toString()
        },
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 50.sp,
            //todo replace with Lato font
            fontFamily = Alegreya,
            fontWeight = FontWeight.W400,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = ColorBackground,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = ColorTextHint
        ),
        onValueChange = { onValueChanged(it) },
        keyboardActions = KeyboardActions(
            onPrevious = {
                moveFocusToPreviousDigit()
            },
            onDone = {
                moveFocusToNextDigit()
            }),
        modifier = Modifier
            .width(55.dp)
            .focusRequester(focusRequester)
    )

}

@Composable
@Preview
fun CodeNumberPreview(){
    CodeNumber(
        value = 3,
        position = 1,
        onCodeDigitChanged = { q, w -> },
        focusManager = LocalFocusManager.current,
        focusRequester = FocusRequester()
    )
}