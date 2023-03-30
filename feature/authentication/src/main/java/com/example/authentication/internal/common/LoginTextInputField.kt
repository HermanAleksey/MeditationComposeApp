package com.example.authentication.internal.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.design_system.AppTheme
import com.example.design_system.ColorPlatinum
import com.example.feature.authentication.R

@Composable
internal fun LoginFlowInputField(
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
        textStyle = MaterialTheme.typography.h5,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.h6.copy(
                    fontSize = labelFontSize.sp,
                    color = labelTextColor
                ),
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
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
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
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
                    labelFontSize = 22
                } else {
                    labelTextColor = Color.White
                    labelFontSize = 15
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

@Preview
@Composable
fun LoginFlowInputFieldPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            LoginFlowInputField(
                textFieldValue = "hello",
                label = "label",
                onValueChanged = {},
                isError = false,
                errorValue = null,
                isEnabled = true,
                focusRequester = FocusRequester(),
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                onKeyboardActions = {},
            )
        }
    }
}