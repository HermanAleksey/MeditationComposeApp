package com.example.authentication.internal.screens.registration

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.State
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authentication.api.registration_screen.RegistrationScreenState
import com.example.authentication.internal.common.LoginFlowBackground
import com.example.authentication.internal.common.LoginFlowInputField
import com.example.authentication.internal.common.LoginMainButton
import com.example.authentication.internal.screens.registration.composable.AlreadyHaveAccountText
import com.example.design_system.AppTheme
import com.example.feature.authentication.R
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun InternalRegistrationScreen(
    uiState: State<RegistrationScreenState>,
    processAction: (RegistrationAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()
    val emailFocusRequester = FocusRequester()

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
                painter = painterResource(id = com.example.core.design_system.R.drawable.ic_app_icon),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(top = 100.dp)
                    .height(40.dp)
            )
            Text(
                text = stringResource(id = R.string.sign_up),
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
                textFieldValue = uiState.value.name,
                isError = uiState.value.nameError != null,
                errorValue = uiState.value.nameError?.asString(),
                label = stringResource(id = R.string.name),
                onValueChanged = {
                    processAction(RegistrationAction.NameTextChanged(it))
                },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                onKeyboardActions = {
                    emailFocusRequester.requestFocus()
                },
            )
            Spacer(modifier = Modifier.height(30.dp))
            LoginFlowInputField(
                isEnabled = !uiState.value.isLoading,
                textFieldValue = uiState.value.login,
                isError = uiState.value.loginError != null,
                errorValue = uiState.value.loginError?.asString(),
                label = stringResource(id = R.string.email_address),
                onValueChanged = {
                    processAction(RegistrationAction.LoginTextChanged(it))
                },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                onKeyboardActions = {
                    passwordFocusRequester.requestFocus()
                },
                focusRequester = emailFocusRequester,
            )
            Spacer(modifier = Modifier.height(30.dp))
            LoginFlowInputField(
                isEnabled = !uiState.value.isLoading,
                textFieldValue = uiState.value.password,
                isError = uiState.value.passwordError != null,
                errorValue = uiState.value.passwordError?.asString(),
                label = stringResource(id = R.string.password),
                onValueChanged = {
                    processAction(RegistrationAction.PasswordTextChanged(it))
                },
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                onKeyboardActions = {
                    focusManager.clearFocus()
                },
                focusRequester = passwordFocusRequester,
            )
            Spacer(modifier = Modifier.height(28.dp))
            LoginMainButton(
                text = stringResource(id = R.string.sign_up).toUpperCase(Locale.current),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                processAction(RegistrationAction.SignUpClick)
            }
            Spacer(modifier = Modifier.height(18.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                AlreadyHaveAccountText(
                    onClick = {
                        processAction(RegistrationAction.SignInClick)
                    }
                )
            }
            Spacer(modifier = Modifier.padding(top = 80.dp))
        }
    }
}

@Preview
@Composable
fun InternalRegistrationScreenPreview() {
    AppTheme {
        InternalRegistrationScreen(
            uiState = MutableStateFlow(RegistrationScreenState()).collectAsState(),
            processAction = {}
        )
    }
}