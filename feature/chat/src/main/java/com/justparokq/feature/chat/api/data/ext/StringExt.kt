package com.justparokq.feature.chat.api.data.ext

import java.nio.charset.Charset

fun String.splitBySize(
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