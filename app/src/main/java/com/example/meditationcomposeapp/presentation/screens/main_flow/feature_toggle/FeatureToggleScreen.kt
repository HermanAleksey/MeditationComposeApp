package com.example.meditationcomposeapp.presentation.screens.main_flow.feature_toggle

import androidx.compose.runtime.Composable
import com.example.feature_toggle.api.FeatureToggleViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun FeatureToggleScreen(
    viewModel: FeatureToggleViewModel,
) {
    com.example.feature_toggle.api.FeatureToggleScreen(viewModel = viewModel)
}
