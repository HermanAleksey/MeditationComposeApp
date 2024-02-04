package com.justparokq.feature.chat.api.data.mapper.chat_ws_data

import com.justparokq.feature.chat.api.data.model.ChatWSDataDto
import com.justparokq.feature.chat.api.data.model.ConnectedDataDto
import com.justparokq.feature.chat.api.data.model.MessageDataDto
import com.justparokq.feature.chat.api.data.model.PublicKeyDataDto
import com.justparokq.feature.chat.api.model.ChatWSData
import dagger.assisted.AssistedFactory
import java.security.PrivateKey
import java.security.PublicKey
import javax.inject.Inject

class ChatWsDataDtoMapper @Inject constructor(
    private val chatWsDataMessageDtoMapper: ChatWsDataMessageDtoMapper,
    private val chatWsDataConnectedDtoMapper: ChatWsDataConnectedDtoMapper,
    private val chatWsDataPublicKeyDtoMapper: ChatWsDataPublicKeyDtoMapper,
) {

    fun mapFrom(objectFrom: ChatWSData, publicServerKey: PublicKey?): ChatWSDataDto {
        return when (objectFrom) {
            is ChatWSData.Message -> {
                if (publicServerKey == null)
                    throw IllegalStateException("Can't parse message without private key")

                chatWsDataMessageDtoMapper.mapToDto(objectFrom, publicServerKey)
            }

            is ChatWSData.Connected -> throw IllegalStateException(
                "You dont need to send this kind of message"
            )

            is ChatWSData.PublicKey -> chatWsDataPublicKeyDtoMapper.mapToDto(objectFrom)
        }
    }


    fun mapTo(objectFrom: ChatWSDataDto, privateClientKey: PrivateKey?): ChatWSData {
        return when (objectFrom) {
            is MessageDataDto -> {
                if (privateClientKey == null)
                    throw IllegalStateException("Can't parse message without private key")

                chatWsDataMessageDtoMapper.mapFromDto(objectFrom, privateClientKey)
            }

            is ConnectedDataDto -> chatWsDataConnectedDtoMapper.map(objectFrom)
            is PublicKeyDataDto -> chatWsDataPublicKeyDtoMapper.mapFromDto(objectFrom)
        }
    }
}

@AssistedFactory
interface ChatWsDataDtoMapperFactory {
    fun create(
        privateClientKey: PrivateKey,
        publicServerKey: PublicKey,
    ): ChatWsDataDtoMapper
}