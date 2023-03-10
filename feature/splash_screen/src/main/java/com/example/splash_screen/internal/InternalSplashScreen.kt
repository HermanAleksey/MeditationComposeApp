package com.example.splash_screen.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.design_system.common_composables.ImageBackground
import com.example.feature.splash_screen.R
import com.example.splash_screen.api.SplashScreenViewModel

@Composable
internal fun InternalSplashScreen(
    viewModel: SplashScreenViewModel,
    currentVersionName: String
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onLaunchSplashScreen(currentVersionName)
    }

    ImageBackground(
        imageRes = R.drawable.background_login
    ) {}
}