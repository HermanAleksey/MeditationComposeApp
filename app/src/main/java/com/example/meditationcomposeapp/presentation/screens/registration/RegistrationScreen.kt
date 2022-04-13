package com.example.meditationcomposeapp.presentation.screens.registration

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.screens.enter.composable.LoginMainButton
import com.example.meditationcomposeapp.presentation.screens.login.composable.LoginFlowBackground
import com.example.meditationcomposeapp.presentation.screens.login.composable.LoginTextInputField
import com.example.meditationcomposeapp.presentation.screens.registration.composable.AlreadyHaveAccountText
import com.example.meditationcomposeapp.ui.theme.Alegreya
import com.example.meditationcomposeapp.ui.theme.ColorBackground

@Composable
fun RegistrationScreen(
    viewModel: RegistrationScreenViewModel,
    setStatusBarColor: (Int) -> Unit,
    navigateToLoginScreen: () -> Unit
) {
    setStatusBarColor(ColorBackground.toArgb())

    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()
    val emailFocusRequester = FocusRequester()

    LoginFlowBackground {
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
                textFieldValue = viewModel.name.value,
                label = stringResource(id = R.string.name),
                onValueChanged = { viewModel.onNameTextChanged(it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                focusManager = focusManager,
                nextFocusRequester = emailFocusRequester
            )
            LoginTextInputField(
                textFieldValue = viewModel.email.value,
                label = stringResource(id = R.string.email_address),
                onValueChanged = { viewModel.onLoginTextChanged(it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                focusManager = focusManager,
                focusRequester = emailFocusRequester,
                nextFocusRequester = passwordFocusRequester
            )
            LoginTextInputField(
                textFieldValue = viewModel.password.value,
                label = stringResource(id = R.string.password),
                onValueChanged = { viewModel.onPasswordTextChanged(it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                focusManager = focusManager,
                focusRequester = passwordFocusRequester
            )
            LoginMainButton(
                text = stringResource(id = R.string.sign_up).toUpperCase(Locale.current),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 28.dp)
            ) {
                viewModel.onSignUpClicked(navigateToLoginScreen)
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
                        viewModel.onSignInClicked(navigateToLoginScreen)
                    })
            }
            Spacer(modifier = Modifier.padding(top = 80.dp))
        }
    }
}