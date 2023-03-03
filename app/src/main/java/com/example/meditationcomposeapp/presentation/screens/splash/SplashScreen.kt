package com.example.meditationcomposeapp.presentation.screens.splash

import androidx.compose.runtime.Composable
import com.example.meditationcomposeapp.BuildConfig
import com.example.splash_screen.api.SplashScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel,
) {
    com.example.splash_screen.api.SplashScreen(
        viewModel = viewModel,
        currentVersionName = BuildConfig.VERSION_NAME
    )
}