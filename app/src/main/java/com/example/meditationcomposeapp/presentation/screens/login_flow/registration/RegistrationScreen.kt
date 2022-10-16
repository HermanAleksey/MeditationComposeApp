package com.example.meditationcomposeapp.presentation.screens.login_flow.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.composable.LoginMainButton
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.composable.LoginFlowBackground
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.composable.LoginFlowInputField
import com.example.meditationcomposeapp.presentation.screens.login_flow.registration.composable.AlreadyHaveAccountText
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun RegistrationScreen(
    viewModel: RegistrationScreenViewModel,
    navigator: DestinationsNavigator,
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()
    val emailFocusRequester = FocusRequester()

    LoginFlowBackground(
        isLoading = viewModel.isLoading()
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
                isEnabled = !viewModel.state.isLoading,
                textFieldValue = viewModel.state.name,
                isError = viewModel.state.nameError != null,
                errorValue = viewModel.state.nameError?.asString(),
                label = stringResource(id = R.string.name),
                onValueChanged = { viewModel.onNameTextChanged(it) },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                onKeyboardActions = {
                    emailFocusRequester.requestFocus()
                },
            )
            LoginFlowInputField(
                isEnabled = !viewModel.state.isLoading,
                textFieldValue = viewModel.state.login,
                isError = viewModel.state.emailError != null,
                errorValue = viewModel.state.emailError?.asString(),
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
                isEnabled = !viewModel.state.isLoading,
                textFieldValue = viewModel.state.password,
                isError = viewModel.state.passwordError != null,
                errorValue = viewModel.state.passwordError?.asString(),
                label = stringResource(id = R.string.password),
                onValueChanged = { viewModel.onPasswordTextChanged(it) },
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                onKeyboardActions = {
                    focusManager.clearFocus()
                },
            )
            LoginMainButton(
                text = stringResource(id = R.string.sign_up).toUpperCase(Locale.current),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 28.dp)
            ) {
                viewModel.onSignUpClicked(navigator)
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
                        viewModel.onSignInClicked(navigator)
                    })
            }
            Spacer(modifier = Modifier.padding(top = 80.dp))
        }
    }
}