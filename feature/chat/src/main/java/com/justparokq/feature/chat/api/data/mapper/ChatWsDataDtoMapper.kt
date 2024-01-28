package com.justparokq.feature.chat.api.data.mapper

import com.justparokq.core.common.mapper.BidirectionalMapper
import com.justparokq.feature.chat.api.data.model.ChatWSDataDto
import com.justparokq.feature.chat.api.data.model.ChatWSDataType
import com.justparokq.feature.chat.api.data.model.ConnectedDataDto
import com.justparokq.feature.chat.api.data.model.MessageDataDto
import com.justparokq.feature.chat.api.data.model.PublicKeyDataDto
import com.justparokq.feature.chat.api.model.ChatWSData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.nio.charset.Charset
import java.security.PrivateKey
import java.security.PublicKey


class ChatWsDataDtoMapper @AssistedInject constructor(
    @Assisted private val privateClientKey: PrivateKey,
    @Assisted private val publicServerKey: PublicKey,
    private val publicKeyStringEncrypt: PublicKeyStringEncrypt,
    private val messageEncrypt: MessageEncrypt,
) : BidirectionalMapper<ChatWSDataDto, ChatWSData> {

    override fun mapFrom(objectFrom: ChatWSData): ChatWSDataDto {
        return when (objectFrom) {
            is ChatWSData.Message -> mapChatWSDataMessage(objectFrom)

            is ChatWSData.Connected -> mapChatWSDataConnected(objectFrom)

            is ChatWSData.PublicKey -> mapChatWSDataPublicKey(objectFrom)
        }
    }

    private fun mapChatWSDataMessage(objectFrom: ChatWSData.Message): MessageDataDto {
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

    private fun String.splitBySize(
        maxSize: Int = 100,
        encoding: Charset = charset("UTF-8"),
    ): List<String> {
        val bytes = toByteArray(encoding)
        val list = ArrayList<String>()
        var start = 0

        while (start < bytes.size) {
            var end = start + maxSize

            if (end > bytes.size) {
                end = bytes.size
            }

            if (bytes[end - 1].toInt() and 0xC0 == 0x80) {
                while (bytes[end - 1].toInt() and 0xC0 == 0x80) {
                    end--
                }
            }

            val subarray = bytes.copyOfRange(start, end)
            val substring = String(subarray, encoding)

            list.add(substring)
            start = end
        }

        return list.toList()
    }

    private fun mapChatWSDataConnected(objectFrom: ChatWSData.Connected): ChatWSDataDto {
        throw IllegalArgumentException("Connected message is supposed only to be received")
    }

    private fun mapChatWSDataPublicKey(objectFrom: ChatWSData.PublicKey): ChatWSDataDto {
        val publicKeyString = publicKeyStringEncrypt.encryptPublicKey(objectFrom.publicKey)
        return PublicKeyDataDto(
            type = ChatWSDataType.PUBLIC_KEY,
            publicKey = publicKeyString
        )
    }

    override fun mapTo(objectFrom: ChatWSDataDto): ChatWSData {
        return when (objectFrom) {
            is MessageDataDto -> mapMessageDataDto(objectFrom)

            is ConnectedDataDto -> mapConnectedDataDto(objectFrom)

            is PublicKeyDataDto -> mapPublicKeyDataDto(objectFrom)
        }
    }

    private fun mapMessageDataDto(objectFrom: MessageDataDto): ChatWSData.Message {
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

    private fun mapConnectedDataDto(objectFrom: ConnectedDataDto): ChatWSData.Connected {
        return ChatWSData.Connected(
            userName = objectFrom.userName
        )
    }


    private fun mapPublicKeyDataDto(objectFrom: PublicKeyDataDto): ChatWSData.PublicKey {
        val publicKey = publicKeyStringEncrypt.decryptPublicKey(objectFrom.publicKey)
        return ChatWSData.PublicKey(
            publicKey = publicKey
        )
    }
}

@AssistedFactory
interface ChatWsDataDtoMapperFactory {
    fun create(
        privateClientKey: PrivateKey,
        publicServerKey: PublicKey,
    ): ChatWsDataDtoMapper
}