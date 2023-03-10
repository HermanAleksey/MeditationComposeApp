package com.example.feature.main.api

import androidx.compose.runtime.Composable
import com.example.feature.main.internal.InternalMainScreen

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {
    InternalMainScreen(viewModel = viewModel)
}