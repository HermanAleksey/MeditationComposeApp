package com.example.meditationcomposeapp.presentation.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.common_composables.ImageBackground
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
            navigator
        )
    })

    ImageBackground(
        imageRes = R.drawable.background_login
    ) {}
}