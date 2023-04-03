package com.example.authentication.api.enter_code_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.authentication.internal.screens.enter_code.EnterCodeAction
import com.example.authentication.internal.screens.enter_code.InternalEnterCodeScreen

@Composable
fun EnterCodeScreen(
    login: String,
    viewModel: EnterCodeScreenViewModel,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.processAction(EnterCodeAction.OnScreenEntered(login))
    }
    InternalEnterCodeScreen(
        uiState = viewModel.uiState.collectAsState(),
        processAction = viewModel::processAction
    )
}