package com.example.splash_screen.internal

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.common.navigation.NavDependenciesProvider
import com.example.design_system.common_composables.ImageBackground
import com.example.feature.splash_screen.R
import com.example.splash_screen.api.SplashScreenNavDependencies
import com.example.splash_screen.api.SplashScreenViewModel

@Composable
internal fun InternalSplashScreen(
    viewModel: SplashScreenViewModel,
    currentVersionName: String
) {
    val navDependencies = ((LocalContext.current as? Activity) as NavDependenciesProvider)
        .provideDependencies(SplashScreenNavDependencies::class.java)

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState()) {
        viewModel.navigationEvent.collect { event ->
            event?.tryNavigate(navDependencies)
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.onLaunchSplashScreen(currentVersionName)
    })

    ImageBackground(
        imageRes = R.drawable.background_login
    ) {}
}