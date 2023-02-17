package com.example.authentication.api.new_password_screen

import androidx.compose.runtime.Composable
import com.example.authentication.internal.screens.new_password.InternalNewPasswordScreen
import com.example.authentication.internal.screens.new_password.NewPasswordScreenViewModel

@Composable
fun NewPasswordScreen(
    viewModel: NewPasswordScreenViewModel,
    login: String,
) {
    InternalNewPasswordScreen(
        viewModel = viewModel,
        login = login,
    )
}