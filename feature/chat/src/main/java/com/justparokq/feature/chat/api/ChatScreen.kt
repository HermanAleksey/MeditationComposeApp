package com.justparokq.feature.chat.api

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.justparokq.feature.chat.internal.presentation.ui.InternalChatScreen

@Composable
fun ChatScreen() {
    val viewModel = ChatScreenViewModel()
    InternalChatScreen(
        uiState = viewModel.uiState.collectAsState().value,
        processAction = viewModel::processAction
    )
}

@Preview
@Composable
fun ChatScreenPreview() {
    MaterialTheme {
        ChatScreen()
    }
}