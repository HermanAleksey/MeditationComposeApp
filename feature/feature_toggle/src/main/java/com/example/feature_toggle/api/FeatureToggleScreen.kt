package com.example.feature_toggle.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.feature_toggle.internal.presentation.InternalFeatureToggleScreen

@Composable
fun FeatureToggleScreen(
    viewModel: FeatureToggleViewModel,
) {
    InternalFeatureToggleScreen(
        uiState = viewModel.uiState.collectAsState(),
        processAction = viewModel::processAction
    )
}