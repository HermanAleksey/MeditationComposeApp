package com.justparokq.feature.chat.api.data.mapper

import com.google.gson.Gson
import com.justparokq.core.common.mapper.BidirectionalMapper
import com.justparokq.feature.chat.api.data.model.ChatWSDataDto
import com.justparokq.feature.chat.api.di.Chat
import javax.inject.Inject

class JsonChatWSDtoMapper @Inject constructor(
    @Chat private val gson: Gson,
) : BidirectionalMapper<String, ChatWSDataDto> {

    override fun mapFrom(objectFrom: ChatWSDataDto): String {
        return gson.toJson(objectFrom)
    }

    override fun mapTo(objectFrom: String): ChatWSDataDto {
        return gson.fromJson(objectFrom, ChatWSDataDto::class.java)
    }
}