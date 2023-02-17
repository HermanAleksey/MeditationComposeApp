package com.example.meditationcomposeapp.presentation.screens.login_flow

import androidx.compose.runtime.Composable
import com.example.authentication.internal.screens.login.LoginScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel
) {
    com.example.authentication.api.login_screen.LoginScreen(
        viewModel = viewModel
    )
}