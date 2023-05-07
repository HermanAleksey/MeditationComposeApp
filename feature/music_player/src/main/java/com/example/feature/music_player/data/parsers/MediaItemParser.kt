package com.example.feature.music_player.data.parsers

import android.support.v4.media.MediaBrowserCompat
import com.example.feature.music_player.data.entities.MediaDataSource
import com.example.feature.music_player.data.entities.Song
import com.example.feature.music_player.data.entities.SongSource
import com.example.feature.music_player.data.entities.toSongSourceType

fun MediaBrowserCompat.MediaItem.toSong(): Song {
    val songSource = description.mediaUri.toSongSourceType()

    return Song(
        mediaId!!,
        description.title.toString(),
        description.subtitle.toString(),
        songSource = when (songSource) {
            SongSource.WEB -> {
                MediaDataSource.WebSource(description.mediaUri.toString())
            }
            SongSource.LOCAL -> {
                MediaDataSource.LocalSource(description.mediaUri.toString().toInt())
            }
        },
        imageSource = when (songSource) {
            SongSource.WEB -> {
                MediaDataSource.WebSource(description.iconUri.toString())
            }
            SongSource.LOCAL -> {
                MediaDataSource.LocalSource(description.iconUri.toString().toInt())
            }
        },
    )
}