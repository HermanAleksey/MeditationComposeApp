package com.justparokq.feature.chat.api.data.mapper

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import com.justparokq.feature.chat.api.model.ChatWSData
import com.justparokq.feature.chat.internal.presentation.model.CurrentUserMessageUIModel
import com.justparokq.feature.chat.internal.presentation.model.MessageUiModel
import com.justparokq.feature.chat.internal.presentation.model.OtherUserMessageUIModel
import java.util.Date
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

    @SuppressLint("SimpleDateFormat")
    fun mapTo(inputText: String?, userName: String?): ChatWSData? {
        inputText ?: return null
        userName ?: return null

        val currentTime = SimpleDateFormat("HH:mm").format(Date())
        return ChatWSData.Message(
            text = inputText,
            userName = userName,
            time = currentTime,
        )
    }
}