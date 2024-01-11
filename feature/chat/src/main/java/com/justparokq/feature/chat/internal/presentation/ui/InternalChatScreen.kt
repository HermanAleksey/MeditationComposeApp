package com.justparokq.feature.chat.internal.presentation.ui

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
import com.justparokq.feature.chat.api.ChatScreenAction
import com.justparokq.feature.chat.api.ChatScreenState
import com.justparokq.feature.chat.internal.presentation.model.CurrentUserMessageUIModel
import com.justparokq.feature.chat.internal.presentation.model.OtherUserMessageUIModel

@Composable
fun InternalChatScreen(
    uiState: ChatScreenState,
    processAction: (ChatScreenAction) -> Unit,
) {
    DefaultAppBackground {
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
                items(uiState.messagesList) {
                    MessageItem(message = it)
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            ChatTextInput(
                text = uiState.inputMessage,
                onTextChanged = { processAction(ChatScreenAction.OnTextInputChanged(it)) },
                onSendClicked = { processAction(ChatScreenAction.OnSendMessageClicked) }
            )
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    MaterialTheme {
        InternalChatScreen(
            uiState = ChatScreenState(
                inputMessage = "",
                messagesList = listOf(
                    OtherUserMessageUIModel(
                        text = "Hello",
                        time = "13:00",
                        userName = "P"
                    ),
                    CurrentUserMessageUIModel(
                        text = "HI!",
                        time = "13:01",
                        isSent = true
                    )
                )
            ),
            processAction = {}
        )
    }
}