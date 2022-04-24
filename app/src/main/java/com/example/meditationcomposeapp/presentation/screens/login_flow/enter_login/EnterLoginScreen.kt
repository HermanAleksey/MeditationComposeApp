package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
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
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.composable.LoginMainButton
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.composable.LoginFlowBackground
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.composable.LoginTextInputField
import com.example.meditationcomposeapp.ui.theme.Alegreya

@Composable
fun EnterLoginScreen(
    viewModel: EnterLoginScreenViewModel,
    navigateToEnterCodeScreen: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val repeatPasswordFocusRequester = FocusRequester()

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
                text = stringResource(id = R.string.password_recovery),
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = Alegreya,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(top = 31.dp)
            )
            Text(
                text = stringResource(id = R.string.enter_login_desc),
                color = Color.White,
                fontSize = 22.sp,
                fontFamily = Alegreya,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .alpha(0.7F)
            )
            LoginTextInputField(
                textFieldValue = viewModel.getEmail(),
                label = stringResource(id = R.string.email_address),
                onValueChanged = { viewModel.onEmailTextChanged(it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                focusManager = focusManager,
                focusRequester = repeatPasswordFocusRequester
            )
            LoginMainButton(
                text = stringResource(id = R.string.confirm).toUpperCase(Locale.current),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 28.dp)
            ) {
                viewModel.onConfirmClick(navigateToEnterCodeScreen)
            }
        }
    }
}