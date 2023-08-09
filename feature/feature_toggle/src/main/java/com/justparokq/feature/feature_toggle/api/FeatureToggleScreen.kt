package com.justparokq.feature.feature_toggle.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.justparokq.feature.feature_toggle.internal.presentation.InternalFeatureToggleScreen

@Composable
fun FeatureToggleScreen(
    viewModel: FeatureToggleScreenViewModel,
) {
    InternalFeatureToggleScreen(
        uiState = viewModel.uiState.collectAsState(),
        processAction = viewModel::processAction
    )
}