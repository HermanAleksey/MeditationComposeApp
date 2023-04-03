package com.example.authentication.api.new_password_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.authentication.internal.screens.new_password.InternalNewPasswordScreen

@Composable
fun NewPasswordScreen(
    viewModel: NewPasswordScreenViewModel,
    login: String,
) {
    InternalNewPasswordScreen(
        login = login,
        uiState = viewModel.uiState.collectAsState(),
        processAction = viewModel::processAction
    )
}