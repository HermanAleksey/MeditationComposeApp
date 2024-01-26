package com.justparokq.feature.chat.api.data.mapper

import com.google.gson.Gson
import com.justparokq.core.common.mapper.BidirectionalMapper
import com.justparokq.feature.chat.api.data.model.ChatWSDataDto
import com.justparokq.feature.chat.api.data.model.ChatWSDataType
import com.justparokq.feature.chat.api.data.model.ConnectedDataDto
import com.justparokq.feature.chat.api.data.model.MessageDataDto
import com.justparokq.feature.chat.api.di.Chat
import com.justparokq.feature.chat.api.model.ChatWSData
import javax.inject.Inject

class ChatWSDataMapper @Inject constructor(
    @Chat private val gson: Gson,
) : BidirectionalMapper<ChatWSData, String> {

    override fun mapFrom(objectFrom: String): ChatWSData {
        val dto = parseJsonToDto(objectFrom)
        return parseDtoToModel(dto)
    }

    override fun mapTo(objectFrom: ChatWSData): String {
        val dto = parseModelToDto(objectFrom)

        return dto?.let { parseDtoToJson(it) } ?: ""
    }

    private fun parseJsonToDto(json: String): ChatWSDataDto {
        return gson.fromJson(json, ChatWSDataDto::class.java)
    }

    private fun parseDtoToModel(dto: ChatWSDataDto): ChatWSData {
        return when (dto) {
            is MessageDataDto -> ChatWSData.Message(
                text = dto.text,
                userName = dto.userName,
                time = dto.time,
            )

            is ConnectedDataDto -> ChatWSData.Connected(
                userName = dto.userName
            )
        }
    }

    private fun parseModelToDto(model: ChatWSData): ChatWSDataDto? {
        return when (model) {
            is ChatWSData.Message -> MessageDataDto(
                type = ChatWSDataType.MESSAGE,
                text = model.text,
                userName = model.userName,
                time = model.time
            )

            is ChatWSData.Connected -> null
        }
    }

    private fun parseDtoToJson(dto: ChatWSDataDto): String {
        return gson.toJson(dto)
    }
}