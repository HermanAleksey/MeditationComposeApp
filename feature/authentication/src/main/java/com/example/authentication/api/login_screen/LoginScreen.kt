package com.example.authentication.api.login_screen

import androidx.compose.runtime.Composable
import com.example.authentication.internal.screens.login.InternalLoginScreen

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel
) {
    InternalLoginScreen(
        viewModel = viewModel
    )
}