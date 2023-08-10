package com.justparokq.feature.authentication.api.enter_login_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.justparokq.feature.authentication.internal.screens.enter_login.InternalEnterLoginScreen
import com.justparokq.core.common.utils.emptyString

@Composable
fun EnterLoginScreen(
    initialLoginValue: String?,
    viewModel: EnterLoginScreenViewModel,
) {
    InternalEnterLoginScreen(
        initialLoginValue = initialLoginValue ?: emptyString(),
        uiState = viewModel.uiState.collectAsState(),
        processAction = viewModel::processAction
    )
}