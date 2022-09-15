package com.example.meditationcomposeapp.presentation.screens.login_flow.enter

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.common_composables.ImageBackground
import com.example.meditationcomposeapp.ui.theme.Alegreya
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.composable.DontHaveAccountText
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.composable.LoginMainButton
import com.example.meditationcomposeapp.ui.theme.ColorBrightToolBar

@OptIn(ExperimentalUnitApi::class)
@Composable
fun EnterScreen(
    viewModel: EnterScreenViewModel,
    setStatusBarColor: (Int) -> Unit,
    navigateToLoginScreen: () -> Unit,
    navigateToRegistrationScreen: () -> Unit,
) {
    setStatusBarColor(ColorBrightToolBar.toArgb())

    ImageBackground(
        imageRes = R.drawable.background_login
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(bottom = 18.dp)
                    .fillMaxWidth(0.5F)
                    .fillMaxHeight(0.5F),
            ) {
                Image(
                    alignment = Alignment.BottomStart,
                    painter = painterResource(id = R.drawable.ic_logo_white),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Logo image",
                )
            }
            Text(
                text = stringResource(id = R.string.welcome),
                fontSize = 34.sp,
                color = Color.White,
                fontFamily = Alegreya,
                fontWeight = FontWeight.W700
            )
            Text(
                text = stringResource(id = R.string.welcome_text),
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontFamily = Alegreya,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.padding(top = 120.dp))
            LoginMainButton(
                stringResource(id = R.string.login_button),
                modifier = Modifier
                    .fillMaxWidth(0.8F)
                    .wrapContentHeight()
            ) {
                viewModel.onEnterClick(navigateToLoginScreen)
            }
            DontHaveAccountText(modifier = Modifier
                .padding(top = 18.dp)
                .clickable {
                    viewModel.onDontHaveAccountClick(navigateToRegistrationScreen)
                })
        }
    }
}


