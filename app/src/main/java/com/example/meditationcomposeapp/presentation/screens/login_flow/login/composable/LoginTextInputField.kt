package com.example.meditationcomposeapp.presentation.screens.login_flow.login.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.ui.theme.Alegreya
import com.example.meditationcomposeapp.ui.theme.ColorBackground
import com.example.meditationcomposeapp.ui.theme.ColorTextHint

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginTextInputField(
    textFieldValue: String,
    label: String,
    onValueChanged: (String) -> Unit,
    //TODO remove default values
    isError: Boolean = true,
    errorValue: String? = "hello error",
    focusManager: FocusManager,
    focusRequester: FocusRequester? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    nextFocusRequester: FocusRequester? = null,
    previousFocusRequester: FocusRequester? = null
) {
    var labelFontSize by remember {
        mutableStateOf(18)
    }
    var labelTextColor by remember {
        mutableStateOf(ColorTextHint)
    }
    TextField(
        value = textFieldValue,
        onValueChange = {
            onValueChanged(it)
        },
        isError = isError,
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = Alegreya,
            fontWeight = FontWeight.W400,
        ),
        label = {
            Text(
                text = label,
                color = labelTextColor,
                fontSize = labelFontSize.sp,
                fontFamily = Alegreya,
                fontWeight = FontWeight.W400,
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            },
            onNext = {
                nextFocusRequester?.requestFocus()
            }
        ),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = ColorBackground,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = ColorTextHint
        ),
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester())
            .onFocusChanged { focusState ->
                if (textFieldValue.isBlank() && !focusState.isFocused) {
                    labelTextColor = ColorTextHint
                    labelFontSize = 18
                } else {
                    labelTextColor = Color.White
                    labelFontSize = 13
                }
            }
    )
    if (errorValue != null) {
        Text(
            text = errorValue,
            color = MaterialTheme.colors.error,
//            modifier = Modifier.align(Alignment.End)
        )
    }
}