package com.example.meditationcomposeapp.presentation.screens.enter

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.ui.theme.Alegreya
import com.example.meditationcomposeapp.ui.theme.MeditationComposeAppTheme
import com.example.meditationcomposeapp.presentation.screens.enter.composable.DontHaveAccountText
import com.example.meditationcomposeapp.presentation.screens.enter.composable.LoginMainButton

@OptIn(ExperimentalUnitApi::class)
@Composable
fun EnterScreen(
    viewModel: EnterScreenViewModel,
    navController: NavController
) {
    Box {
        Image(
            painter = painterResource(id = R.mipmap.background_login),
            contentDescription = "Background image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
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
                fontSize = TextUnit(34F, TextUnitType.Sp),
                color = Color.White,
                fontFamily = Alegreya,
                fontWeight = FontWeight.W700
            )
            Text(
                text = stringResource(id = R.string.welcome_text),
                fontSize = TextUnit(20F, TextUnitType.Sp),
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
            ) { viewModel.onLoginButtonClicked(navController) }
            DontHaveAccountText(modifier = Modifier
                .padding(top = 18.dp)
                .clickable {
                    viewModel.onSignUpClicked(navController)
                })
        }
    }
}