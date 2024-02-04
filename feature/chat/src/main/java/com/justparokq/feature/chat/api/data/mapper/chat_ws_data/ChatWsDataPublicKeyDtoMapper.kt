package com.justparokq.feature.chat.api.data.mapper.chat_ws_data

import com.justparokq.core.common.utils.emptyString
import com.justparokq.feature.chat.api.data.mapper.PublicKeyStringEncrypt
import com.justparokq.feature.chat.api.data.model.ChatWSDataDto
import com.justparokq.feature.chat.api.data.model.ChatWSDataType
import com.justparokq.feature.chat.api.data.model.PublicKeyDataDto
import com.justparokq.feature.chat.api.model.ChatWSData
import javax.inject.Inject

class ChatWsDataPublicKeyDtoMapper @Inject constructor(
    private val publicKeyStringEncrypt: PublicKeyStringEncrypt,
) {

    fun mapToDto(objectFrom: ChatWSData.PublicKey): ChatWSDataDto {
        val publicKeyString = publicKeyStringEncrypt.encryptPublicKey(objectFrom.publicKey)
        return PublicKeyDataDto(
            type = ChatWSDataType.PUBLIC_KEY,
            publicKey = publicKeyString,
            userName = emptyString()
        )
    }

    fun mapFromDto(objectFrom: PublicKeyDataDto): ChatWSData.PublicKey {
        val publicKey = publicKeyStringEncrypt.decryptPublicKey(objectFrom.publicKey)
        return ChatWSData.PublicKey(
            publicKey = publicKey,
            userName = objectFrom.userName
        )
    }
}