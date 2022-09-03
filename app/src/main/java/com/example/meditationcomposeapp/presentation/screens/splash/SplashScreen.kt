package com.example.meditationcomposeapp.presentation.screens.splash

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import com.example.meditationcomposeapp.data_source.utils.TAG.TAG_D
import com.example.meditationcomposeapp.presentation.navigation.navigateFunc

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel,
    navigateToEnterScreen: navigateFunc,
    navigateToHomeScreen: navigateFunc
) {
    LaunchedEffect(key1 = true, block = {
        Log.d(TAG_D, "SplashScreen: launching splash screen")
        viewModel.onLaunchSplashScreen(
            navigateToEnterScreen, navigateToHomeScreen
        )
    })
}