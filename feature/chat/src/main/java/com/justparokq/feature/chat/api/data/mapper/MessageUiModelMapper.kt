package com.justparokq.feature.chat.api.data.mapper

import com.justparokq.feature.chat.api.model.ChatWSData
import com.justparokq.feature.chat.internal.presentation.model.CurrentUserMessageUIModel
import com.justparokq.feature.chat.internal.presentation.model.MessageUiModel
import com.justparokq.feature.chat.internal.presentation.model.OtherUserMessageUIModel
import javax.inject.Inject

class MessageUiModelMapper @Inject constructor() {

    fun mapFrom(message: ChatWSData.Message, currentUserName: String): MessageUiModel {
        return if (message.userName == currentUserName)
            CurrentUserMessageUIModel(
                text = message.text,
                time = message.time
            )
        else
            OtherUserMessageUIModel(
                text = message.text,
                time = message.time,
                userName = message.userName
            )
    }

    fun mapTo(uiModel: MessageUiModel): ChatWSData {

    }
}