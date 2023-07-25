package com.example.design_system.common_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ColorBackground(
    isLoading: Boolean = false,
    lockScreenWhenLoading: Boolean = true,
    color: Color,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color = color)
            .fillMaxSize()
    ) {
        content()

        if (lockScreenWhenLoading && isLoading)
            BlurScreenLayer()

        if (isLoading)
            LoadingElement()
    }
}

