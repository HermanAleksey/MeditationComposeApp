package com.justparokq.feature.music_player.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toTimeString(): String {
    val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
    return dateFormat.format(this)
}