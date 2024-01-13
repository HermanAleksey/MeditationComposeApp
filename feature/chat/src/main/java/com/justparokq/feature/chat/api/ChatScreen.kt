package com.justparokq.feature.chat.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.justparokq.feature.chat.internal.presentation.ui.InternalChatScreen

@Composable
fun ChatScreen(viewModel: ChatScreenViewModel) {
    InternalChatScreen(
        uiState = viewModel.uiState.collectAsState().value,
        processAction = viewModel::processAction
    )
}