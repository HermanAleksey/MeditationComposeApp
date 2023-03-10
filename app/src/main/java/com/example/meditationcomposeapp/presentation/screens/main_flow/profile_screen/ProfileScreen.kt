package com.example.meditationcomposeapp.presentation.screens.main_flow.profile_screen

import androidx.compose.runtime.Composable
import com.example.feature.profile.api.ProfileScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel,
) {
    com.example.feature.profile.api.ProfileScreen(viewModel = viewModel)
}