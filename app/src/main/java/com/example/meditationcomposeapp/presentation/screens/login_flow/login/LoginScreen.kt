package com.example.meditationcomposeapp.presentation.screens.login_flow.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.navigation.processEvent
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.composable.DontHaveAccountText
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.composable.LoginMainButton
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.composable.LoginFlowBackground
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.composable.LoginFlowInputField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel,
    navigator: DestinationsNavigator,
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState()) {
        viewModel.navigationEvent.collect { event ->
            event.processEvent(navigator)
        }
    }

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
                contentDescription = "Background image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(top = 100.dp)
                    .height(40.dp)
            )
            Text(
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(top = 31.dp)
            )
            Text(
                text = stringResource(id = R.string.sign_in_desc),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .alpha(0.7F)
            )
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
                    style = MaterialTheme.typography.caption,
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
                DontHaveAccountText(modifier = Modifier
                    .padding(top = 18.dp)
                    .clickable {
                        viewModel.onSignUpClicked()
                    })
            }
            Spacer(modifier = Modifier.padding(top = 80.dp))
        }
    }
}