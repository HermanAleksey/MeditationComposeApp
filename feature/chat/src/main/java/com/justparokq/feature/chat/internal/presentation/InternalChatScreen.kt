package com.justparokq.feature.chat.internal.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.common_composables.DefaultAppBackground
import com.justparokq.feature.chat.api.ChatScreenViewModel

@Composable
fun InternalChatScreen(viewModel: ChatScreenViewModel) {
    DefaultAppBackground() {
        Column(modifier = Modifier.fillMaxSize(), Arrangement.Bottom) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
            ) {
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
                items(viewModel.getMessages()) {
                    MessageItem(message = it)
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            ChatTextInput(
                text = viewModel.textInputStr,
                onTextChanged = { viewModel.updateInput(it) },
                onSendClicked = { viewModel.onSendClick() }
            )
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    MaterialTheme {
        InternalChatScreen(viewModel = ChatScreenViewModel())
    }
}