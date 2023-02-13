package com.example.authentication.api.enter_code_screen

import androidx.compose.runtime.Composable
import com.example.authentication.internal.screens.enter_code.EnterCodeScreenViewModel
import com.example.authentication.internal.screens.enter_code.InternalEnterCodeScreen

@Composable
fun EnterCodeScreen(
    login: String,
    viewModel: EnterCodeScreenViewModel,
) {
    InternalEnterCodeScreen(login, viewModel)
}