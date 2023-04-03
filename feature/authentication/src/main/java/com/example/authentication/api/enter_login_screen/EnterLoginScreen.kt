package com.example.authentication.api.enter_login_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.authentication.internal.screens.enter_login.InternalEnterLoginScreen
import com.example.common.utils.emptyString

@Composable
fun EnterLoginScreen(
    initialLoginValue: String?,
    viewModel: EnterLoginScreenViewModel,
) {
    InternalEnterLoginScreen(
        initialLoginValue = initialLoginValue ?: emptyString(),
        uiState = viewModel.uiState.collectAsState(),
        processAction = viewModel::processAction
    )
}