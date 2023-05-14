package com.example.feature.music_player.data.parsers

import android.support.v4.media.MediaMetadataCompat
import com.example.feature.music_player.data.entities.MediaDataSource
import com.example.feature.music_player.data.entities.Song
import com.example.feature.music_player.data.entities.SongSource
import com.example.feature.music_player.data.entities.toSongSourceType


fun MediaMetadataCompat.toSong(): Song? {
    return description?.let {
        val songSource = it.mediaUri.toSongSourceType()

        Song(
            mediaId = it.mediaId ?: "",
            title = it.title.toString(),
            subtitle = it.subtitle.toString(),
            songSource = when (songSource) {
                SongSource.WEB -> {
                    MediaDataSource.WebSource(it.mediaUri.toString())
                }
                SongSource.LOCAL -> {
                    MediaDataSource.LocalSource(it.mediaUri.toString().toInt())
                }
            },
            imageSource = when (songSource) {
                SongSource.WEB -> {
                    MediaDataSource.WebSource(it.iconUri.toString())
                }
                SongSource.LOCAL -> {
                    MediaDataSource.LocalSource(it.iconUri.toString().toInt())
                }
            },
        )
    }
}

fun Song.toMediaMetadataCompat(): MediaMetadataCompat {
     val mediaMetadataCompat = MediaMetadataCompat.Builder()
        .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
        .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, title)
        .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, subtitle)
        .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, subtitle)
        .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaId)
        .apply {
            when (songSource) {
                is MediaDataSource.WebSource -> {
                    putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, songSource.url)
                }
                is MediaDataSource.LocalSource -> {
                    putString(
                        MediaMetadataCompat.METADATA_KEY_MEDIA_URI,
                        songSource.resId.toString()
                    )
                }
            }

            when (imageSource) {
                is MediaDataSource.WebSource -> {
                    putString(
                        MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI,
                        imageSource.url
                    )
                    putString(
                        MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI,
                        imageSource.url
                    )
                }
                is MediaDataSource.LocalSource -> {
                    putString(
                        MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI,
                        imageSource.resId.toString()
                    )
                    putString(
                        MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI,
                        imageSource.resId.toString()
                    )
                }
            }
        }
        .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION, subtitle)
        .build()

    return mediaMetadataCompat
}