package com.example.feature.music_player.data.parser

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import com.example.feature.music_player.data.entities.DataSourceMapper
import com.example.feature.music_player.data.entities.LocalRes
import com.example.feature.music_player.data.entities.LocalSong
import com.example.feature.music_player.data.entities.Song
import com.example.feature.music_player.data.entities.WebSong
import com.example.feature.music_player.data.entities.WebURL
import javax.inject.Inject
import kotlin.random.Random

class SongParser @Inject constructor() {

    fun parse(webSong: WebSong): Song {
        return Song(
            mediaId = webSong.mediaId,
            title = webSong.title,
            subtitle = webSong.subtitle,
            songSource = WebURL(webSong.songUrl),
            imageSource = WebURL(webSong.imageUrl)
        )
    }

    fun parse(localSong: LocalSong): Song {
        return Song(
            mediaId = localSong.mediaId,
            title = localSong.title,
            subtitle = localSong.subtitle,
            songSource = LocalRes(localSong.songResId),
            imageSource = LocalRes(localSong.imageResId)
        )
    }

    fun parse(mediaItem: MediaBrowserCompat.MediaItem): Song = with(mediaItem) {
        val songDataSource = description.mediaUri?.toString()?.let {
            DataSourceMapper().getDataSourceType(it)
        } ?: throw Exception("Can't cast URI to data source")
        val imageDataSource = description.iconUri?.toString()?.let {
            DataSourceMapper().getDataSourceType(it)
        } ?: throw Exception("Can't cast URI to data source")

        return Song(
            mediaId = mediaId ?: Random.nextInt().toString(),
            title = description.title.toString(),
            subtitle = description.subtitle.toString(),
            songSource = songDataSource,
            imageSource = imageDataSource,
        )
    }

    fun parse(metadataCompat: MediaMetadataCompat): Song? {
        return metadataCompat.description?.let { it ->
            val songDataSource = it.mediaUri?.toString()?.let {
                DataSourceMapper().getDataSourceType(it)
            } ?: throw Exception("Can't cast URI to data source")
            val imageDataSource = it.iconUri?.toString()?.let {
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

}