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
            songSource = MediaDataSource.LocalSource(songResId),
            imageSource = MediaDataSource.LocalSource(imageResId)
        )
    }
}

data class Song(
    val mediaId: String = "",
    val title: String = "",
    val subtitle: String = "",
    val songSource: MediaDataSource,
    val imageSource: MediaDataSource
)

sealed interface MediaDataSource {

    data class WebSource(
        val url: String
    ) : MediaDataSource

    //todo replace resId with path or smth. Add DB
    data class LocalSource(
        val resId: Int
    ) : MediaDataSource
}