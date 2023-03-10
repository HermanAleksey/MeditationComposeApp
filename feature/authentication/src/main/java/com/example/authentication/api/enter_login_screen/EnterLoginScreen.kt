package com.example.authentication.api.enter_login_screen

import androidx.compose.runtime.Composable
import com.example.authentication.internal.screens.enter_login.InternalEnterLoginScreen

@Composable
fun EnterLoginScreen(
    initialLoginValue: String?,
    viewModel: EnterLoginScreenViewModel,
) {
    InternalEnterLoginScreen(
        initialLoginValue = initialLoginValue,
        viewModel = viewModel,
    )
}