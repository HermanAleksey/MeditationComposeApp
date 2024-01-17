package com.justparokq.mediose.presentation.screens.main_flow.chat

import androidx.compose.runtime.Composable
import com.justparokq.feature.chat.api.presentation.ChatScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ChatScreen(
    viewModel: ChatScreenViewModel,
) {
    com.justparokq.feature.chat.api.presentation.ChatScreen(
        viewModel = viewModel,
    )
}
