package com.example.meditationcomposeapp.presentation.screens.login_flow

import androidx.compose.runtime.Composable
import com.example.authentication.internal.screens.enter.EnterScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun EnterScreen(
    viewModel: EnterScreenViewModel,
) {
    com.example.authentication.api.enter_screen.EnterScreen(viewModel = viewModel)
}


