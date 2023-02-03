package com.example.authentication.internal.screens.enter_login

import com.example.feature.authentication.R
import androidx.compose.foundation.Image
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
import com.example.common.view_model.processEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun EnterLoginScreen(
    initialLoginValue: String?,
    viewModel: EnterLoginScreenViewModel,
    navigator: DestinationsNavigator
) {
    val focusManager = LocalFocusManager.current
    val repeatPasswordFocusRequester = FocusRequester()

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = null, block = {
        viewModel.onLoginTextChanged(initialLoginValue ?: "")
    })

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
                contentDescription = "App logo",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(top = 100.dp)
                    .height(40.dp)
            )
            Text(
                text = stringResource(id = R.string.password_recovery),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(top = 31.dp)
            )
            Text(
                text = stringResource(id = R.string.enter_login_desc),
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
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Email,
                focusRequester = repeatPasswordFocusRequester,
                onKeyboardActions = {
                    focusManager.clearFocus()
                }
            )
            LoginMainButton(
                text = stringResource(id = R.string.confirm).toUpperCase(Locale.current),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 28.dp)
            ) {
                viewModel.onConfirmClick()
            }
        }
    }
}