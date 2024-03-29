package com.justparokq.feature.authentication.internal.screens.enter_login

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
import androidx.compose.runtime.LaunchedEffect
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
import com.justparokq.feature.authentication.api.enter_login_screen.EnterLoginAction
import com.justparokq.feature.authentication.api.enter_login_screen.EnterLoginScreenState
import com.justparokq.feature.authentication.internal.common.LoginFlowInputField
import com.justparokq.feature.authentication.internal.common.LoginMainButton
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun InternalEnterLoginScreen(
    initialLoginValue: String,
    processAction: (EnterLoginAction) -> Unit,
    uiState: State<EnterLoginScreenState>,
) {
    LaunchedEffect(key1 = Unit) {
        processAction(EnterLoginAction.FirstLaunch(initialLoginValue))
    }

    val focusManager = LocalFocusManager.current
    val repeatPasswordFocusRequester = FocusRequester()

    DefaultAppBackground(
        isLoading = uiState.value.isLoading,
        testTag = stringResource(id = R.string.enter_login_screen_test_tag)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 35.dp, end = 34.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = com.justparokq.core.design_system.R.drawable.ic_app_icon),
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
                text = stringResource(id = R.string.enter_login_desc),
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
                onValueChanged = { processAction(EnterLoginAction.LoginTextChanged(it)) },
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Email,
                focusRequester = repeatPasswordFocusRequester,
                onKeyboardActions = {
                    focusManager.clearFocus()
                },
                testTag = stringResource(id = R.string.login_field_enter_login_screen_test_tag)
            )
            LoginMainButton(
                text = stringResource(id = R.string.confirm).toUpperCase(Locale.current),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 28.dp)
            ) {
                processAction(EnterLoginAction.ConfirmClick)
            }
        }
    }
}

@Preview
@Composable
fun EnterLoginScreenPreview() {
    AppTheme {
        InternalEnterLoginScreen(
            initialLoginValue = "",
            processAction = {},
            uiState = MutableStateFlow(EnterLoginScreenState()).collectAsState()
        )
    }
}