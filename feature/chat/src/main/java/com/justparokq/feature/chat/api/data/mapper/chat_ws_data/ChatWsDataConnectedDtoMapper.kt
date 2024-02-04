package com.justparokq.feature.chat.api.data.mapper.chat_ws_data

import com.justparokq.feature.chat.api.data.model.ConnectedDataDto
import com.justparokq.feature.chat.api.model.ChatWSData
import javax.inject.Inject

class ChatWsDataConnectedDtoMapper @Inject constructor() {

    fun map(objectFrom: ConnectedDataDto): ChatWSData.Connected {
        return ChatWSData.Connected(
            userName = objectFrom.userName
        )
    }
}