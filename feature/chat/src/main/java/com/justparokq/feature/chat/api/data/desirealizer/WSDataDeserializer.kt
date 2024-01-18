package com.justparokq.feature.chat.api.data.desirealizer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.justparokq.feature.chat.api.data.model.ChatWSDataDto
import com.justparokq.feature.chat.api.data.model.ChatWSDataType
import java.lang.reflect.Type


private const val CONTENT_TYPE = "type"

class WSDataDeserializer : JsonDeserializer<ChatWSDataDto> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
    ): ChatWSDataDto? {
        if (json.isJsonNull) return null

        val handlerTypeStr = json.asJsonObject.get(CONTENT_TYPE)?.asString ?: return null
        val serverDrivenEventHandlerType =
            ChatWSDataType.parseFromString(handlerTypeStr) ?: return null

        return context.deserialize<ChatWSDataDto>(
            json, serverDrivenEventHandlerType.drivenClass
        )
    }
}