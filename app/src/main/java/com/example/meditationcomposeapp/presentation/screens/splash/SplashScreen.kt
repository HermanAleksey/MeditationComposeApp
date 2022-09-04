package com.example.meditationcomposeapp.presentation.screens.splash

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.data_source.data_store.userDataStore
import com.example.meditationcomposeapp.data_source.utils.TAG.TAG_D
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
        Log.d(TAG_D, "SplashScreen: launching splash screen")
        viewModel.onLaunchSplashScreen(
            navigateToEnterScreen, navigateToHomeScreen
        )
    })


    ImageBackground(
        imageRes = R.drawable.background_login
    ){}
}