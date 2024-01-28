package com.justparokq.feature.chat.api.data.mapper

import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.Base64
import javax.inject.Inject


class PublicKeyStringEncrypt @Inject constructor() {

    fun encryptPublicKey(key: PublicKey): String {
        val keyBytes: ByteArray = key.encoded
        return Base64.getEncoder().encodeToString(keyBytes)
    }

    fun decryptPublicKey(keyString: String): PublicKey {
        val keyBytes: ByteArray = Base64.getDecoder().decode(keyString)
        val keySpec = X509EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePublic(keySpec)
    }
}