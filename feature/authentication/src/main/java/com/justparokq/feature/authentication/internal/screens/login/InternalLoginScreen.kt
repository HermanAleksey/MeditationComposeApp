package com.justparokq.feature.authentication.internal.screens.login

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
import com.justparokq.core.design_system.AppTheme
import com.justparokq.core.design_system.common_composables.DefaultAppBackground
import com.justparokq.feature.authentication.R
import com.justparokq.feature.authentication.api.login_screen.LoginAction
import com.justparokq.feature.authentication.api.login_screen.LoginScreenState
import com.justparokq.feature.authentication.internal.common.LoginFlowInputField
import com.justparokq.feature.authentication.internal.common.LoginMainButton
import com.justparokq.feature.authentication.internal.screens.enter.composable.DontHaveAccountText
import kotlinx.coroutines.flow.MutableStateFlow
import com.justparokq.core.design_system.R as dsR

@Composable
internal fun InternalLoginScreen(
    uiState: State<LoginScreenState>,
    processAction: (LoginAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()

    DefaultAppBackground(
        isLoading = uiState.value.isLoading,
        testTag = stringResource(id = R.string.login_screen_test_tag)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 35.dp, end = 34.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = dsR.drawable.ic_app_icon),
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
                onValueChanged = { processAction(LoginAction.LoginTextChanged(it)) },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                onKeyboardActions = {
                    passwordFocusRequester.requestFocus()
                },
                testTag = stringResource(id = R.string.login_field_login_screen_test_tag)
            )
            Spacer(modifier = Modifier.height(30.dp))
            LoginFlowInputField(
                isEnabled = !uiState.value.isLoading,
                textFieldValue = uiState.value.password,
                label = stringResource(id = R.string.password),
                isError = uiState.value.passwordError != null,
                errorValue = uiState.value.passwordError?.asString(),
                onValueChanged = { processAction(LoginAction.PasswordTextChanged(it)) },
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
                            processAction(LoginAction.ForgotPasswordClick)
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
                processAction(LoginAction.LoginClick)
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
                        processAction(LoginAction.SignUpClick)
                    }
                )
            }
            Spacer(modifier = Modifier.padding(top = 80.dp))
        }
    }
}

@Preview
@Composable
fun InternalLoginScreenPreview() {
    AppTheme {
        InternalLoginScreen(
            uiState = MutableStateFlow(LoginScreenState()).collectAsState(),
            processAction = {}
        )
    }
}