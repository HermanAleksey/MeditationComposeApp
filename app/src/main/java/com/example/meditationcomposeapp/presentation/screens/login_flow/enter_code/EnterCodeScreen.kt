package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.composable.LoginFlowBackground
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code.composable.CodePanel
import com.example.meditationcomposeapp.ui.theme.Alegreya
import com.example.meditationcomposeapp.ui.theme.MeditationComposeAppTheme

@Composable
fun EnterCodeScreen(
    viewModel: EnterCodeScreenViewModel,
    navigateToNewPasswordScreen: () -> Unit
) {
    /**
     * Use to transmit navigation method and
     * also serve as callback for event when
     * last digit on CodePanel was filled
     * */
    fun onLastDigitFilled() {
        viewModel.onLastDigitFilled(navigateToNewPasswordScreen)
    }

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
                text = stringResource(id = R.string.password_recovery_desc),
                color = Color.White,
                fontSize = 22.sp,
                fontFamily = Alegreya,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .alpha(0.7F)
            )
            CodePanel(viewModel.getCode(), viewModel::onCodeDigitChanged, ::onLastDigitFilled)
            Spacer(modifier = Modifier.padding(top = 80.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeditationComposeAppTheme {
        EnterCodeScreen(viewModel = EnterCodeScreenViewModel(hiltViewModel())) {}
    }
}