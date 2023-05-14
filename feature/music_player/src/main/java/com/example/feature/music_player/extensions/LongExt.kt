package com.example.musicplayer.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toTimeString(): String {
    val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
    return dateFormat.format(this)
}