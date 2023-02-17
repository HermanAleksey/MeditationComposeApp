package com.example.meditationcomposeapp.presentation.screens.login_flow

import androidx.compose.runtime.Composable
import com.example.authentication.internal.screens.new_password.NewPasswordScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun NewPasswordScreen(
    viewModel: NewPasswordScreenViewModel,
    login: String,
) {
    com.example.authentication.api.new_password_screen.NewPasswordScreen(
        viewModel = viewModel,
        login = login,
    )
}