package com.example.meditationcomposeapp.presentation.screens.login_flow

import androidx.compose.runtime.Composable
import com.example.authentication.api.registration_screen.RegistrationScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun RegistrationScreen(
    viewModel: RegistrationScreenViewModel
) {
    com.example.authentication.api.registration_screen.RegistrationScreen(
        viewModel = viewModel
    )
}