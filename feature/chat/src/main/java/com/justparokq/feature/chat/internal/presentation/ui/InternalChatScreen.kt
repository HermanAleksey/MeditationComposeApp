package com.justparokq.feature.chat.internal.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
        when (uiState) {
            is ChatScreenState.Success -> SuccessChatScreen(uiState, processAction)
            is ChatScreenState.Loading -> LoadingChatScreen()
            is ChatScreenState.Error -> ErrorChatScreen(uiState, processAction)
        }
    }
}

@Composable
private fun SuccessChatScreen(
    uiState: ChatScreenState.Success,
    processAction: (ChatScreenAction) -> Unit,
) {
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

@Composable
private fun LoadingChatScreen() {
    Text(text = "LOADING (> <)")
}

@Composable
private fun ErrorChatScreen(
    uiState: ChatScreenState.Error,
    processAction: (ChatScreenAction) -> Unit,
) {
    Column {
        Text(text = "Error text: ${uiState.errorText}")
        Button(onClick = { processAction(ChatScreenAction.OnTryReconnectButtonClicked) }) {
            Text(text = "Retry")
        }
    }
}

@Preview(name = "Success connect")
@Composable
fun ChatScreenPreview() {
    MaterialTheme {
        InternalChatScreen(
            uiState = ChatScreenState.Success(
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

@Preview(name = "Error connect")
@Composable
fun ChatScreenPreviewError() {
    MaterialTheme {
        InternalChatScreen(
            uiState = ChatScreenState.Error(
                errorText = "Error while trying to connect to the chat"
            ),
            processAction = {}
        )
    }
}

@Preview(name = "Loading ")
@Composable
fun ChatScreenPreviewLoading() {
    MaterialTheme {
        InternalChatScreen(
            uiState = ChatScreenState.Loading,
            processAction = {}
        )
    }
}