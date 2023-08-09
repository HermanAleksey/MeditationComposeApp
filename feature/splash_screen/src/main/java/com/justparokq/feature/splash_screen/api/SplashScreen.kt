package com.justparokq.feature.splash_screen.api

import androidx.compose.runtime.Composable
import com.justparokq.feature.splash_screen.internal.InternalSplashScreen

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel,
    currentVersionName: String
) {
    InternalSplashScreen(onLaunch = {
        viewModel.onLaunchSplashScreen(currentVersionName)
    })
}