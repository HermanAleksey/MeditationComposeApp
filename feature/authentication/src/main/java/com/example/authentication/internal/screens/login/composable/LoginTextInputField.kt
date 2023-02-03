package com.example.authentication.internal.screens.login.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.design_system.ColorPlatinum
import com.example.design_system.Montserrat
import com.example.feature.authentication.R

@Composable
fun LoginFlowInputField(
    textFieldValue: String,
    label: String,
    onValueChanged: (String) -> Unit,
    isError: Boolean,
    errorValue: String?,
    isEnabled: Boolean = true,
    focusRequester: FocusRequester? = null,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    onKeyboardActions: () -> Unit,
) {
    val isPasswordField = remember {
        keyboardType == KeyboardType.Password
    }
    var passwordVisible by rememberSaveable { mutableStateOf(!isPasswordField) }
    var labelFontSize by remember {
        mutableStateOf(18)
    }
    var labelTextColor by remember {
        mutableStateOf(ColorPlatinum)
    }
    TextField(
        enabled = isEnabled,
        value = textFieldValue,
        onValueChange = {
            onValueChanged(it)
        },
        isError = isError,
        textStyle = MaterialTheme.typography.body2,
        label = {
            Text(
                text = label,
                color = labelTextColor,
                fontSize = labelFontSize.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.W400,
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = KeyboardActions(
            onDone = {
                onKeyboardActions()
            },
            onNext = {
                onKeyboardActions()
            }
        ),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = MaterialTheme.colors.onBackground
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            if (!isPasswordField) return@TextField

            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Please provide localized description for accessibility services
            val description =
                if (passwordVisible) stringResource(R.string.hide_password)
                else stringResource(R.string.show_password)

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = image,
                    tint = Color.White,
                    contentDescription = description
                )
            }
        },
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester())
            .onFocusChanged { focusState ->
                if (textFieldValue.isBlank() && !focusState.isFocused) {
                    labelTextColor = ColorPlatinum
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
        )
    }
}