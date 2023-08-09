package com.justparokq.feature.authentication.api.registration_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.justparokq.feature.authentication.internal.screens.registration.InternalRegistrationScreen

@Composable
fun RegistrationScreen(
    viewModel: RegistrationScreenViewModel
) {
    InternalRegistrationScreen(
        uiState = viewModel.uiState.collectAsState(),
        processAction = viewModel::processAction
    )
}