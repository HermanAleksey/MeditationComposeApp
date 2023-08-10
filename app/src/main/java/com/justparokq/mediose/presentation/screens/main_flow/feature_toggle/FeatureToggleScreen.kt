package com.justparokq.mediose.presentation.screens.main_flow.feature_toggle

import androidx.compose.runtime.Composable
import com.justparokq.feature.feature_toggle.api.FeatureToggleScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun FeatureToggleScreen(
    viewModel: FeatureToggleScreenViewModel,
) {
    com.justparokq.feature.feature_toggle.api.FeatureToggleScreen(viewModel = viewModel)
}
