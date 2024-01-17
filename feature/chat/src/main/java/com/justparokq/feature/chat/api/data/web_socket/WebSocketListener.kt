package com.justparokq.feature.chat.api.data.web_socket

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

class ChatWebSocketListener @Inject constructor() : WebSocketListener() {

    private val TAG = "TAGG"

    private val _isWebSocketConnected: MutableStateFlow<WebSocketConnectionStatus> =
        MutableStateFlow(WebSocketConnectionStatus.Idle)
    val isWebSocketConnected = _isWebSocketConnected.asStateFlow()

    private val _messagesFlow = MutableStateFlow("")
    val messagesFlow = _messagesFlow.asStateFlow()

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        _isWebSocketConnected.update { WebSocketConnectionStatus.Connected }

        webSocket.send("Android Device Connected")
        Log.d(TAG, "onOpen:")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        _messagesFlow.tryEmit(text)
        Log.d(TAG, "onMessage: $text")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        _isWebSocketConnected.update { WebSocketConnectionStatus.Disconnecting(code, reason) }
        Log.d(TAG, "onClosing: $code $reason")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        _isWebSocketConnected.update { WebSocketConnectionStatus.Disconnected(code, reason) }
        Log.d(TAG, "onClosed: $code $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.d(TAG, "onFailure: ${t.message} $response")
        _isWebSocketConnected.update { WebSocketConnectionStatus.Error(t, response) }
        super.onFailure(webSocket, t, response)
    }
}