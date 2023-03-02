package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import androidx.compose.runtime.Composable
import com.example.feature.main.api.MainScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
) {
    com.example.feature.main.api.MainScreen(viewModel = viewModel)
}