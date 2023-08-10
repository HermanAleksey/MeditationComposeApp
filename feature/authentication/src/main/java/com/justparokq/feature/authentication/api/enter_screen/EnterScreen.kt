package com.justparokq.feature.authentication.api.enter_screen

import androidx.compose.runtime.Composable
import com.justparokq.feature.authentication.internal.screens.enter.InternalEnterScreen

@Composable
fun EnterScreen(
    viewModel: EnterScreenViewModel,
) {
    InternalEnterScreen(
        onEnterClick = viewModel::onEnterClick,
        onDontHaveAccountClick = viewModel::onDontHaveAccountClick
    )
}