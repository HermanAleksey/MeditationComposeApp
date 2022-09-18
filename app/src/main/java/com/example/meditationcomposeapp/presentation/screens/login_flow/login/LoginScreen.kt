package com.example.meditationcomposeapp.presentation.screens.login_flow.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.composable.DontHaveAccountText
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.composable.LoginMainButton
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.composable.LoginFlowBackground
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.composable.LoginTextInputField
import com.example.meditationcomposeapp.ui.theme.Alegreya
import com.example.meditationcomposeapp.ui.theme.ColorBackground
import com.example.meditationcomposeapp.ui.theme.ColorTextHint
import com.example.meditationcomposeapp.ui.theme.MeditationComposeAppTheme


@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel,
    setStatusBarColor: (Int) -> Unit,
    navigateToEnterLoginScreen: (String) -> Unit,
    navigateToMainScreen: () -> Unit,
    navigateToRegistrationScreen: () -> Unit,
) {
    setStatusBarColor(ColorBackground.toArgb())

    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()

    LoginFlowBackground(
        isLoading = viewModel.state.isLoading
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 35.dp, end = 34.dp)
                .verticalScroll(rememberScrollState())
                .blur(16.dp)
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
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = Alegreya,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(top = 31.dp)
            )
            Text(
                text = stringResource(id = R.string.sign_in_desc),
                color = Color.White,
                fontSize = 22.sp,
                fontFamily = Alegreya,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .alpha(0.7F)
            )
            LoginTextInputField(
                textFieldValue = viewModel.state.login,
                label = stringResource(id = R.string.email_address),
                isError = viewModel.state.loginError != null,
                errorValue = viewModel.state.loginError?.asString(),
                onValueChanged = { viewModel.onLoginTextChanged(it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                focusManager = focusManager,
                nextFocusRequester = passwordFocusRequester
            )
            LoginTextInputField(
                textFieldValue = viewModel.state.password,
                label = stringResource(id = R.string.password),
                isError = viewModel.state.passwordError != null,
                errorValue = viewModel.state.passwordError?.asString(),
                onValueChanged = { viewModel.onPasswordTextChanged(it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                focusManager = focusManager,
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
                    color = ColorTextHint,
                    fontSize = 14.sp,
                    fontFamily = Alegreya,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier
                        .padding(top = 9.dp)
                        .clickable {
                            viewModel.onForgotPasswordClicked(
                                navigateToEnterLoginScreen
                            )
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
                viewModel.onLoginClicked(navigateToMainScreen)
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
                        viewModel.onSignUpClicked(navigateToRegistrationScreen)
                    })
            }
            Spacer(modifier = Modifier.padding(top = 80.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeditationComposeAppTheme {
        LoginScreen(
            viewModel = viewModel(),
            setStatusBarColor = {},
            navigateToEnterLoginScreen = { },
            navigateToMainScreen = { },
            navigateToRegistrationScreen = {})
    }
}