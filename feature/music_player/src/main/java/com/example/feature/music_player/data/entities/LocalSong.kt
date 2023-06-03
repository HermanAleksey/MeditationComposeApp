package com.example.feature.music_player.data.entities

class LocalSong(
    val mediaId: String = "",
    val title: String = "",
    val subtitle: String = "",
    val songResId: Int,
    val imageResId: Int,
) {
    fun toSong(): Song {
        return Song(
            mediaId = mediaId,
            title = title,
            subtitle = subtitle,
            songSource = LocalRes(songResId),
            imageSource = LocalRes(imageResId)
        )
    }
}