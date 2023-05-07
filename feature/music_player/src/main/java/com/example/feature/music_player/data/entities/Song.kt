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

data class WebSong(
    val mediaId: String = "",
    val title: String = "",
    val subtitle: String = "",
    val songUrl: String = "",
    val imageUrl: String = "",
) {
    fun toSong(): Song {
       return Song(
            mediaId = mediaId,
            title = title,
            subtitle = subtitle,
            songSource = MediaDataSource.WebSource(songUrl),
            imageSource = MediaDataSource.WebSource(imageUrl)
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

    data class LocalSource(
        val resId: Int
    ) : MediaDataSource
}