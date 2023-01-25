package com.example.meditationcomposeapp.presentation.screens.login_flow.enter

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.design_system.common_composables.ImageBackground
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.navigation.processEvent
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.composable.DontHaveAccountText
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.composable.LoginMainButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun EnterScreen(
    viewModel: EnterScreenViewModel,
    navigator: DestinationsNavigator,
) {
    val activity = LocalContext.current as? Activity
    BackHandler(enabled = true, onBack = {
        activity?.finish()
    })

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState()) {
        viewModel.navigationEvent.collect { event ->
            event.processEvent(navigator)
        }
    }

    ImageBackground(
        imageRes = R.drawable.background_login
    ) {
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
                style = MaterialTheme.typography.h1,
            )
            Text(
                text = stringResource(id = R.string.welcome_text),
                style = MaterialTheme.typography.body1.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(top = 120.dp))
            LoginMainButton(
                stringResource(id = R.string.login_button),
                modifier = Modifier
                    .fillMaxWidth(0.8F)
                    .wrapContentHeight()
            ) {
                viewModel.onEnterClick()
            }
            DontHaveAccountText(modifier = Modifier
                .padding(top = 18.dp)
                .clickable {
                    viewModel.onDontHaveAccountClick()
                })
        }
    }
}


