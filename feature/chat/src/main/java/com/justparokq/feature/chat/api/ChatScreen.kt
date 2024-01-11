package com.justparokq.feature.chat.api

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.justparokq.feature.chat.internal.presentation.InternalChatScreen

@Composable
fun ChatScreen() {
    val viewModel = ChatScreenViewModel()
    InternalChatScreen(viewModel)
}

@Preview
@Composable
fun ChatScreenPreview() {
    MaterialTheme {
        ChatScreen()
    }
}