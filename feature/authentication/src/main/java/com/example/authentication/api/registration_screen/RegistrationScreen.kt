package com.example.authentication.api.registration_screen

import androidx.compose.runtime.Composable
import com.example.authentication.internal.screens.registration.InternalRegistrationScreen

@Composable
fun RegistrationScreen(
    viewModel: RegistrationScreenViewModel
) {
    InternalRegistrationScreen(
        viewModel = viewModel
    )
}