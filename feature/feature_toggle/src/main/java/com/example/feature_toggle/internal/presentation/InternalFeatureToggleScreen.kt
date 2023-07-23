package com.example.feature_toggle.internal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.feature_toggle.api.FeatureToggleAction
import com.example.feature_toggle.api.FeatureToggleScreenState
import com.example.feature_toggle.api.FeatureToggleViewModel

@Composable
internal fun InternalFeatureToggleScreen(
    uiState: State<FeatureToggleScreenState>,
    processAction: (FeatureToggleAction) -> Unit,
) {

}