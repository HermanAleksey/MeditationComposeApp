package com.example.meditationcomposeapp.presentation.screens.login_flow

import androidx.compose.runtime.Composable
import com.example.authentication.api.enter_login_screen.EnterLoginScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun EnterLoginScreen(
    initialLoginValue: String?,
    viewModel: EnterLoginScreenViewModel,
) {
    com.example.authentication.api.enter_login_screen.EnterLoginScreen(
        initialLoginValue = initialLoginValue,
        viewModel = viewModel,
    )
}