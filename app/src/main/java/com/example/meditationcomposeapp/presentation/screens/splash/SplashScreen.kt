package com.example.meditationcomposeapp.presentation.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.data_source.utils.printEventLog
import com.example.meditationcomposeapp.presentation.common_composables.ImageBackground
import com.example.meditationcomposeapp.presentation.navigation.navigateFunc
import com.example.meditationcomposeapp.ui.theme.ColorBrightToolBar

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel,
    setStatusBarColor: (Int) -> Unit,
    navigateToEnterScreen: navigateFunc,
    navigateToHomeScreen: navigateFunc
) {
    setStatusBarColor(ColorBrightToolBar.toArgb())

    LaunchedEffect(key1 = true, block = {
        this.javaClass.printEventLog("Launching splash screen")
        viewModel.onLaunchSplashScreen(
            navigateToEnterScreen, navigateToHomeScreen
        )
    })


    ImageBackground(
        imageRes = R.drawable.background_login
    ){}
}