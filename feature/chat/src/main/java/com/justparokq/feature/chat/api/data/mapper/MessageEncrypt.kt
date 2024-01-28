package com.justparokq.feature.chat.api.data.mapper

import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher
import javax.inject.Inject

private const val RSA_ALGORITHM = "RSA/ECB/PKCS1Padding"

class MessageEncrypt @Inject constructor() {

    private val cipher: Cipher = Cipher.getInstance(RSA_ALGORITHM)

    fun encryptMessage(message: String, publicKey: PublicKey): ByteArray {
        val messageBytes = message.toByteArray()
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return cipher.doFinal(messageBytes)
    }

    fun decryptMessage(message: ByteArray, privateKey: PrivateKey): String {
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val decryptedBytes = cipher.doFinal(message)
        return String(decryptedBytes)
    }
}