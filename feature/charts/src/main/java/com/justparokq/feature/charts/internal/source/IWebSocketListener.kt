package com.justparokq.feature.charts.internal.source

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

interface IWebSocketListener {

    var onMessageReceived: ((text: String) -> Unit)?

    fun sendMessage(text: String)

    fun closeConnection()
}

class WebSocketListenerImpl(
    okHttpClient: OkHttpClient,
    request: Request,
) : IWebSocketListener {

    private val webSocketListener = object : WebSocketListener() {
        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            onMessageReceived?.invoke(text)
        }
    }

    private var webSocket: WebSocket = okHttpClient.newWebSocket(request, webSocketListener)

    override var onMessageReceived: ((text: String) -> Unit)? = null

    override fun sendMessage(text: String) {
        webSocket.send(text)
    }

    override fun closeConnection() {
        webSocket.cancel()
    }
}