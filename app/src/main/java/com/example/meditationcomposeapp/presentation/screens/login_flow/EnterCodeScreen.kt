package com.example.meditationcomposeapp.presentation.screens.login_flow

import androidx.compose.runtime.Composable
import com.example.authentication.internal.screens.enter.EnterScreenViewModel
import com.example.authentication.internal.screens.enter_code.EnterCodeScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun EnterCodeScreen(
    viewModel: EnterCodeScreenViewModel,
    login: String
) {
    com.example.authentication.api.enter_code_screen.EnterCodeScreen(
        viewModel = viewModel,
        login = login
    )
}