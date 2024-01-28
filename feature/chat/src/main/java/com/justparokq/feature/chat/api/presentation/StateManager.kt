package com.justparokq.feature.chat.api.presentation

import com.justparokq.core.common.utils.emptyString
import com.justparokq.feature.chat.internal.presentation.model.MessageUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.security.PrivateKey
import java.security.PublicKey

class StateManager(
    private val uiState: MutableStateFlow<ChatScreenState>,
) {

    fun updateUserName(userName: String) {
        (uiState.value as? ChatScreenState.Success)?.let { previousState ->
            uiState.update {
                previousState.copy(
                    userName = userName
                )
            }
        }
    }

    fun addMessageToChat(uiMessage: MessageUiModel) {
        (uiState.value as? ChatScreenState.Success)?.let { currentState ->
            uiState.update {
                currentState.copy(
                    messagesList = currentState.messagesList + uiMessage
                )
            }
        }
    }

    fun getCurrentUserName(): String? {
        return (uiState.value as? ChatScreenState.Success)?.userName
    }

    fun updateServerPublicKey(publicKey: PublicKey) {
        (uiState.value as? ChatScreenState.Success)?.let { previousState ->
            uiState.update {
                previousState.copy(
                    serverPublicKey = publicKey
                )
            }
        }
    }

    fun getInputText(): String? {
        return (uiState.value as? ChatScreenState.Success)?.inputMessage
    }

    fun updateInputText(text: String) {
        (uiState.value as? ChatScreenState.Success)?.let { previousState ->
            uiState.update {
                previousState.copy(
                    inputMessage = text
                )
            }
        }
    }

    fun clearInputText() {
        (uiState.value as? ChatScreenState.Success)?.let { previousState ->
            uiState.update {
                previousState.copy(
                    inputMessage = emptyString()
                )
            }
        }
    }

    fun setEmptySuccessState() {
        uiState.update {
            ChatScreenState.Success(
                inputMessage = emptyString(),
                messagesList = listOf(),
                userName = emptyString(),
                serverPublicKey = null,
                clientPublicKey = null,
                clientPrivateKey = null,
            )
        }
    }

    fun updateKeys(publicKey: PublicKey, privateKey: PrivateKey) {
        (uiState.value as? ChatScreenState.Success)?.let { previousState ->
            uiState.update {
                previousState.copy(
                    clientPublicKey = publicKey,
                    clientPrivateKey = privateKey,
                )
            }
        }
    }

    fun getClientPublicKey(): PublicKey? {
        return (uiState.value as? ChatScreenState.Success)?.clientPublicKey
    }

    fun getServerPublicKey(): PublicKey? {
        return (uiState.value as? ChatScreenState.Success)?.serverPublicKey
    }

    fun getClientPrivateKey(): PrivateKey? {
        return (uiState.value as? ChatScreenState.Success)?.clientPrivateKey
    }
}