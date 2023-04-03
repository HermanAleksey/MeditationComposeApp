package com.example.splash_screen.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.design_system.AppTheme
import com.example.design_system.common_composables.ImageBackground
import com.example.feature.splash_screen.R

@Composable
internal fun InternalSplashScreen(
    onLaunch: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        onLaunch()
    }

    ImageBackground(
        imageRes = R.drawable.background_login
    ) {}
}

@Preview
@Composable
fun InternalSplashScreenPreview() {
    AppTheme {
        InternalSplashScreen {}
    }
}