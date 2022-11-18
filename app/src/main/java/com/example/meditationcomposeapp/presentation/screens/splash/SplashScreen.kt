package com.example.meditationcomposeapp.presentation.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.common_composables.ImageBackground
import com.example.meditationcomposeapp.presentation.screens.destinations.LoginScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel,
    navigator: DestinationsNavigator,
) {
    LaunchedEffect(key1 = true, block = {
        viewModel.onLaunchSplashScreen(
            navigateToLoginScreen = {
                navigator.navigate(LoginScreenDestination)
            },
            navigateToMainScreen = {
                navigator.navigate(MainScreenDestination)
            }
        )
    })

    ImageBackground(
        imageRes = R.drawable.background_login
    ) {}
}