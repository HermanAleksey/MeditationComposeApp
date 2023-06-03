package com.example.feature.music_player.data.parsers

import android.support.v4.media.MediaMetadataCompat
import com.example.feature.music_player.data.entities.DataSourceMapper
import com.example.feature.music_player.data.entities.Song


fun MediaMetadataCompat.toSong(): Song? {
    return description?.let {
        val songDataSource = description.mediaUri?.toString()?.let {
            DataSourceMapper().getDataSourceType(it)
        } ?: throw Exception("Can't cast URI to data source")
        val imageDataSource = description.iconUri?.toString()?.let {
            DataSourceMapper().getDataSourceType(it)
        } ?: throw Exception("Can't cast URI to data source")


        Song(
            mediaId = it.mediaId ?: "",
            title = it.title.toString(),
            subtitle = it.subtitle.toString(),
            songSource = songDataSource,
            imageSource = imageDataSource,
        )
    }
}

fun Song.toMediaMetadataCompat(): MediaMetadataCompat {
    return MediaMetadataCompat.Builder()
        .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
        .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, title)
        .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, subtitle)
        .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, subtitle)
        .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaId)
        .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, songSource.getTypedValue())
        .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, imageSource.getTypedValue())
        .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, imageSource.getTypedValue())
        .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION, subtitle)
        .build()
}