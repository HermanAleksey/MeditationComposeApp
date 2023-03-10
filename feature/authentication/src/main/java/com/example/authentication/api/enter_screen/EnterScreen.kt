package com.example.authentication.api.enter_screen

import androidx.compose.runtime.Composable
import com.example.authentication.internal.screens.enter.InternalEnterScreen

@Composable
fun EnterScreen(
    viewModel: EnterScreenViewModel,
) {
    InternalEnterScreen(viewModel)
}