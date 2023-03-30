package com.example.authentication.internal.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import com.example.authentication.api.login_screen.LoginScreenViewModel
import com.example.authentication.internal.common.LoginFlowBackground
import com.example.authentication.internal.common.LoginFlowInputField
import com.example.authentication.internal.common.LoginMainButton
import com.example.authentication.internal.screens.enter.composable.DontHaveAccountText
import com.example.feature.authentication.R

@Composable
internal fun InternalLoginScreen(
    viewModel: LoginScreenViewModel,
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()

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
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(top = 31.dp)
            )
            Text(
                text = stringResource(id = R.string.sign_in_desc),
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .alpha(0.7F)
            )
            Spacer(modifier = Modifier.height(40.dp))
            LoginFlowInputField(
                isEnabled = !uiState.value.isLoading,
                textFieldValue = uiState.value.login,
                label = stringResource(id = R.string.email_address),
                isError = uiState.value.loginError != null,
                errorValue = uiState.value.loginError?.asString(),
                onValueChanged = { viewModel.onLoginTextChanged(it) },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                onKeyboardActions = {
                    passwordFocusRequester.requestFocus()
                },
            )
            Spacer(modifier = Modifier.height(30.dp))
            LoginFlowInputField(
                isEnabled = !uiState.value.isLoading,
                textFieldValue = uiState.value.password,
                label = stringResource(id = R.string.password),
                isError = uiState.value.passwordError != null,
                errorValue = uiState.value.passwordError?.asString(),
                onValueChanged = { viewModel.onPasswordTextChanged(it) },
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                onKeyboardActions = {
                    focusManager.clearFocus()
                },
                focusRequester = passwordFocusRequester
            )
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .padding(top = 9.dp)
                        .clickable {
                            viewModel.onForgotPasswordClicked()
                        }
                )
            }
            LoginMainButton(
                text = stringResource(id = R.string.login).toUpperCase(Locale.current),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 28.dp)
            ) {
                viewModel.onLoginClicked()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Spacer(modifier = Modifier.height(18.dp))
                DontHaveAccountText(
                    onClick = {
                        viewModel.onSignUpClicked()
                    }
                )
            }
            Spacer(modifier = Modifier.padding(top = 80.dp))
        }
    }
}