package com.example.feature.music_player.data.parsers

import android.support.v4.media.MediaBrowserCompat
import com.example.feature.music_player.data.entities.DataSourceMapper
import com.example.feature.music_player.data.entities.Song

fun MediaBrowserCompat.MediaItem.toSong(): Song {
    val songDataSource = description.mediaUri?.toString()?.let {
        DataSourceMapper().getDataSourceType(it)
    } ?: throw Exception("Can't cast URI to data source")
    val imageDataSource = description.iconUri?.toString()?.let {
        DataSourceMapper().getDataSourceType(it)
    } ?: throw Exception("Can't cast URI to data source")

    return Song(
        mediaId = mediaId!!,
        title = description.title.toString(),
        subtitle = description.subtitle.toString(),
        songSource = songDataSource,
        imageSource = imageDataSource,
    )
}