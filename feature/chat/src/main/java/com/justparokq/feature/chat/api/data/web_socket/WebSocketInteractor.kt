package com.justparokq.feature.chat.api.data.web_socket

import com.justparokq.feature.chat.api.di.Chat
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

private const val NORMAL_WS_CLOSURE_CODE = 1000
private const val NORMAL_WS_CLOSURE_TEXT = "Canceled manually."
private const val WEB_SOCKET_URL = "ws://192.168.100.22:8580/chat"

class WebSocketInteractor @Inject constructor(
    @Chat private val okHttpClient: OkHttpClient,
) {

    fun createWebSocket(
        webSocketListener: WebSocketListener,
    ): WebSocket {
        try {
            return okHttpClient.newWebSocket(createRequestToWebSocket(), webSocketListener)
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception()
        }
    }

    private fun createRequestToWebSocket(): Request {
        return Request.Builder()
            .url(WEB_SOCKET_URL)
            .build()
    }

    fun disconnectFromWebSocket(webSocket: WebSocket) {
        webSocket.close(NORMAL_WS_CLOSURE_CODE, NORMAL_WS_CLOSURE_TEXT)
    }

    //call on destroy
    fun shutDown() {
        okHttpClient.dispatcher.executorService.shutdown()
    }
}