package com.example.authentication.internal.screens.login.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.design_system.common_composables.ColorBackground
import com.example.feature.authentication.R

@Composable
fun LoginFlowBackground(
    isLoading: Boolean = false,
    content: @Composable () -> Unit
) {
    ColorBackground(
        content = {
            DecorationLeavesTop()
            DecorationLeavesBottom()
            content()
        },
        color = MaterialTheme.colors.background,
        isLoading = isLoading
    )
}

@Composable
private fun DecorationLeavesBottom() {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_login_leaves),
            contentDescription = "Background image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(1.2f)
                .wrapContentHeight()
        )
    }
}

@Composable
private fun DecorationLeavesTop() {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.single_plant_top),
            contentDescription = "Background image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .wrapContentHeight()
        )
    }
}