package com.justparokq.feature.chat.api.data.web_socket

import okhttp3.Response

sealed interface WebSocketConnectionStatus {

    object Idle : WebSocketConnectionStatus

    object Connected : WebSocketConnectionStatus

    data class Disconnecting(
        val code: Int,
        val reason: String,
    ) : WebSocketConnectionStatus

    data class Disconnected(
        val code: Int,
        val reason: String,
    ) : WebSocketConnectionStatus

    data class Error(
        val exception: Throwable,
        val response: Response?,
    ) : WebSocketConnectionStatus
}