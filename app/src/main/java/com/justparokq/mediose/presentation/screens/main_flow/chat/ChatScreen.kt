package com.justparokq.mediose.presentation.screens.main_flow.chat

import androidx.compose.runtime.Composable
import com.justparokq.feature.chat.api.ChatScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ChatScreen(
    viewModel: ChatScreenViewModel,
) {
    com.justparokq.feature.chat.api.ChatScreen(
        viewModel = viewModel,
    )
}
