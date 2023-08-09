package com.justparokq.mediose.presentation.screens.main_flow.updates_history

import androidx.compose.runtime.Composable
import com.justparokq.feature.update_history.api.UpdatesDescriptionScreen
import com.justparokq.feature.update_history.api.UpdatesDescriptionViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun UpdatesHistoryScreen(
    viewModel: UpdatesDescriptionViewModel,
) {
    UpdatesDescriptionScreen(viewModel = viewModel)
}
