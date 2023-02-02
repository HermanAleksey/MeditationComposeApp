package com.example.meditationcomposeapp.presentation.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.common.view_model.processEvent
import com.example.design_system.common_composables.ImageBackground
import com.example.meditationcomposeapp.R
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
    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState()) {
        viewModel.navigationEvent.collect { event ->
            event.processEvent(navigator)
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.onLaunchSplashScreen()
    })

    ImageBackground(
        imageRes = R.drawable.background_login
    ) {}
}