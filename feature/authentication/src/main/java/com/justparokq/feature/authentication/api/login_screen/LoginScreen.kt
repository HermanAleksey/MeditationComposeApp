package com.justparokq.feature.authentication.api.login_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.justparokq.feature.authentication.internal.screens.login.InternalLoginScreen

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel,
) {
    InternalLoginScreen(
        uiState = viewModel.uiState.collectAsState(),
        processAction = viewModel::processAction
    )
}