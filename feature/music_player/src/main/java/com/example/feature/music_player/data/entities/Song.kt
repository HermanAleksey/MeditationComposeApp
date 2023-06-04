package com.example.feature.music_player.data.entities

data class Song(
    val mediaId: String = "",
    val title: String = "",
    val subtitle: String = "",
    val songSource: DataSource,
    val imageSource: DataSource
)