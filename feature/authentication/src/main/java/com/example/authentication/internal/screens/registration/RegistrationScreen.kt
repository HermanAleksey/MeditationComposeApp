package com.example.authentication.internal.screens.registration

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
import com.example.authentication.internal.enter.composable.LoginMainButton
import com.example.authentication.internal.login.composable.LoginFlowBackground
import com.example.authentication.internal.login.composable.LoginFlowInputField
import com.example.authentication.internal.registration.composable.AlreadyHaveAccountText
import com.example.common.view_model.processEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.feature.authentication.R

@Destination
@Composable
fun RegistrationScreen(
    viewModel: RegistrationScreenViewModel,
    navigator: DestinationsNavigator,
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()
    val emailFocusRequester = FocusRequester()

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
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(top = 100.dp)
                    .height(40.dp)
            )
            Text(
                text = stringResource(id = R.string.sign_up),
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
                textFieldValue = uiState.value.name,
                isError = uiState.value.nameError != null,
                errorValue = uiState.value.nameError?.asString(),
                label = stringResource(id = R.string.name),
                onValueChanged = { viewModel.onNameTextChanged(it) },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                onKeyboardActions = {
                    emailFocusRequester.requestFocus()
                },
            )
            LoginFlowInputField(
                isEnabled = !uiState.value.isLoading,
                textFieldValue = uiState.value.login,
                isError = uiState.value.loginError != null,
                errorValue = uiState.value.loginError?.asString(),
                label = stringResource(id = R.string.email_address),
                onValueChanged = { viewModel.onLoginTextChanged(it) },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                onKeyboardActions = {
                    passwordFocusRequester.requestFocus()
                },
                focusRequester = emailFocusRequester,
            )
            LoginFlowInputField(
                isEnabled = !uiState.value.isLoading,
                textFieldValue = uiState.value.password,
                isError = uiState.value.passwordError != null,
                errorValue = uiState.value.passwordError?.asString(),
                label = stringResource(id = R.string.password),
                onValueChanged = { viewModel.onPasswordTextChanged(it) },
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                onKeyboardActions = {
                    focusManager.clearFocus()
                },
                focusRequester = passwordFocusRequester,
            )
            LoginMainButton(
                text = stringResource(id = R.string.sign_up).toUpperCase(Locale.current),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 28.dp)
            ) {
                viewModel.onSignUpClicked()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                AlreadyHaveAccountText(modifier = Modifier
                    .padding(top = 18.dp)
                    .clickable {
                        viewModel.onSignInClicked()
                    })
            }
            Spacer(modifier = Modifier.padding(top = 80.dp))
        }
    }
}