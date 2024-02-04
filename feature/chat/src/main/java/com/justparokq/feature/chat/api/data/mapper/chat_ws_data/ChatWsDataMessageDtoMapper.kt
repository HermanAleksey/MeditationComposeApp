package com.justparokq.feature.chat.api.data.mapper.chat_ws_data

import com.justparokq.feature.chat.api.data.ext.splitBySize
import com.justparokq.feature.chat.api.data.mapper.MessageEncrypt
import com.justparokq.feature.chat.api.data.model.ChatWSDataType
import com.justparokq.feature.chat.api.data.model.MessageDataDto
import com.justparokq.feature.chat.api.model.ChatWSData
import java.security.PrivateKey
import java.security.PublicKey
import javax.inject.Inject

class ChatWsDataMessageDtoMapper @Inject constructor(
    private val messageEncrypt: MessageEncrypt,
) {

    fun mapToDto(objectFrom: ChatWSData.Message, publicServerKey: PublicKey): MessageDataDto {
        val encodedTextArray = objectFrom.text
            .splitBySize()
            .map { partString ->
                messageEncrypt.encryptMessage(
                    message = partString,
                    publicKey = publicServerKey
                )
            }

        return MessageDataDto(
            type = ChatWSDataType.MESSAGE,
            text = encodedTextArray,
            userName = objectFrom.userName,
            time = objectFrom.time
        )
    }

    fun mapFromDto(objectFrom: MessageDataDto, privateClientKey: PrivateKey): ChatWSData.Message {
        val decodedText = StringBuilder()
        objectFrom.text.forEach { encodedMessage ->
            val decodedPart = messageEncrypt.decryptMessage(
                message = encodedMessage,
                privateKey = privateClientKey
            )
            decodedText.append(decodedPart)
        }

        return ChatWSData.Message(
            text = decodedText.toString(),
            userName = objectFrom.userName,
            time = objectFrom.time,
        )
    }
}