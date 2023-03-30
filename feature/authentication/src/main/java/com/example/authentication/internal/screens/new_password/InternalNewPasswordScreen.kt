package com.example.authentication.internal.screens.new_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.example.authentication.api.new_password_screen.NewPasswordScreenViewModel
import com.example.authentication.internal.common.LoginFlowBackground
import com.example.authentication.internal.common.LoginFlowInputField
import com.example.authentication.internal.common.LoginMainButton
import com.example.feature.authentication.R

@Composable
internal fun InternalNewPasswordScreen(
    viewModel: NewPasswordScreenViewModel,
    login: String,
) {
    val focusManager = LocalFocusManager.current
    val repeatPasswordFocusRequester = FocusRequester()

    val uiState = viewModel.uiState.collectAsState()

    LoginFlowBackground(
        isLoading = uiState.value.isLoading
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 35.dp, end = 34.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_white),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(top = 100.dp)
                    .height(40.dp)
            )
            Text(
                text = stringResource(id = R.string.password_recovery),
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(top = 31.dp)
            )
            Text(
                text = stringResource(id = R.string.new_password_desc),
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .alpha(0.7F)
            )
            Spacer(modifier = Modifier.height(40.dp))
            LoginFlowInputField(
                isEnabled = !uiState.value.isLoading,
                textFieldValue = uiState.value.newPassword,
                label = stringResource(id = R.string.new_password),
                isError = uiState.value.newPasswordError != null,
                errorValue = uiState.value.newPasswordError?.asString(),
                onValueChanged = { viewModel.onNewPasswordTextChanged(it) },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password,
                onKeyboardActions = {
                    repeatPasswordFocusRequester.requestFocus()
                },
            )
            Spacer(modifier = Modifier.height(30.dp))
            LoginFlowInputField(
                isEnabled = !uiState.value.isLoading,
                textFieldValue = uiState.value.repeatPassword,
                label = stringResource(id = R.string.repeat_new_password),
                isError = uiState.value.repeatPasswordError != null,
                errorValue = uiState.value.repeatPasswordError?.asString(),
                onValueChanged = { viewModel.onRepeatPasswordTextChanged(it) },
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                onKeyboardActions = {
                    focusManager.clearFocus()
                },
                focusRequester = repeatPasswordFocusRequester
            )
            LoginMainButton(
                text = stringResource(id = R.string.confirm).toUpperCase(Locale.current),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 28.dp)
            ) {
                viewModel.onConfirmClick(login)
            }
        }
    }
}