package com.example.splash_screen.api

import androidx.compose.runtime.Composable
import com.example.splash_screen.internal.InternalSplashScreen
import com.example.splash_screen.internal.SplashScreenViewModel

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel,
    currentVersionName: String
) {
    InternalSplashScreen(viewModel, currentVersionName = currentVersionName)
}