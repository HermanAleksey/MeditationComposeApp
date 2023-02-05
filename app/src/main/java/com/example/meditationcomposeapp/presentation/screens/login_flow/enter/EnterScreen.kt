package com.example.meditationcomposeapp.presentation.screens.login_flow.enter

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import com.example.authentication.internal.screens.enter.EnterScreenViewModel
import com.example.authentication.internal.screens.enter_code.EnterCodeScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun EnterScreen(
    viewModel: EnterScreenViewModel,
    navigator: DestinationsNavigator,
) {
    com.example.authentication.api.EnterScreen(viewModel = viewModel, navigator = navigator)
}


