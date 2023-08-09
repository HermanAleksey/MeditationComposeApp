package com.justparokq.feature.update_history.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.justparokq.feature.update_history.internal.InternalUpdatesDescriptionScreen

@Composable
fun UpdatesDescriptionScreen(
    viewModel: UpdatesDescriptionViewModel,
) {
    val updatesList = viewModel.uiState.collectAsState()
    InternalUpdatesDescriptionScreen(
        uiState = updatesList.value,
        onCancelRateUsClick = viewModel::onCancelRateUsClick
    )
}