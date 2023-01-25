package com.example.design_system.common_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun ColorBackground(
    isLoading: Boolean = false,
    lockScreenWhenLoading: Boolean = true,
    color: Color,
    content: @Composable () -> Unit,
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

