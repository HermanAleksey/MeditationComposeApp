package com.example.musicplayer.data.entities

import android.net.Uri

enum class SongSource {
    WEB, LOCAL
}

fun Uri?.toSongSourceType(): SongSource {
    if (this == null) throw java.lang.RuntimeException("Song Uri is null")
    return if (this.toString().toIntOrNull() != null)
        SongSource.LOCAL else SongSource.WEB
}