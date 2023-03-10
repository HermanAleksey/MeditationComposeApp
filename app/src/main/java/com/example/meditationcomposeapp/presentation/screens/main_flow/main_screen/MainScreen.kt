package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature.main.api.MainScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(viewModel = hiltViewModel())
}

@Destination
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
) {
    com.example.feature.main.api.MainScreen(viewModel = viewModel)
}