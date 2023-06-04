package com.example.feature.music_player.exoplayer.music_source

import android.support.v4.media.MediaMetadataCompat
import com.example.feature.music_player.data.entities.LocalRes
import com.example.feature.music_player.data.entities.LocalURL
import com.example.feature.music_player.data.entities.Song
import com.example.feature.music_player.data.entities.WebURL
import com.example.feature.music_player.data.parser.SongParser
import com.example.feature.music_player.data.provider.MusicProvider
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MusicSource(
    private val source: MusicProvider,
    private val dataSourceFactory: DefaultDataSource.Factory,
    private val songParser: SongParser,
) {
    private val onReadyListeners = mutableListOf<(Boolean) -> Unit>()
    var mediaMetadataCompats = emptyList<MediaMetadataCompat>()

    private var sourceState: SourceState = SourceState.CREATED
        set(value) {
            if (value == SourceState.INITIALIZED || value == SourceState.ERROR) {
                synchronized(onReadyListeners) {
                    field = value
                    onReadyListeners.forEach { listener ->
                        listener(sourceState == SourceState.INITIALIZED)
                    }
                }
            }
        }

    fun getConcatenatedMediaSource(): ConcatenatingMediaSource {
        val concatenatingMediaSource = ConcatenatingMediaSource()
        mediaMetadataCompats.forEach { metadataCompat ->
            songParser.parse(metadataCompat)
            val songDataSource = songParser.parse(metadataCompat) ?: throw Throwable("Hello")

            val mediaSource = when (songDataSource.songSource) {
                is LocalRes -> {
                    val rawResId = songDataSource.songSource.resId
                    parseLocalSongToMediaSource(rawResId)
                }
                is WebURL -> {
                    val url = songDataSource.songSource.url
                    parseWebSongToMediaSource(url)
                }
                is LocalURL -> throw Throwable("Not implemented yet")
            }
            concatenatingMediaSource.addMediaSource(mediaSource)
        }
        return concatenatingMediaSource
    }

    private fun parseLocalSongToMediaSource(rawResId: Int) =
        ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(
                MediaItem.fromUri(
                    RawResourceDataSource.buildRawResourceUri(rawResId)
                )
            )

    private fun parseWebSongToMediaSource(uri: String) =
        ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(
                MediaItem.fromUri(uri)
            )

    suspend fun fetchMediaData() = withContext(Dispatchers.IO) {
        withContext(Dispatchers.Main) {
            sourceState = SourceState.INITIALIZING
        }

        val allSongs = source.getMusic()

        mediaMetadataCompats = allSongs.map { song ->
            parseSongToMetadataCompat(song)
        }

        withContext(Dispatchers.Main) {
            sourceState = SourceState.INITIALIZED
        }
    }

    private fun parseSongToMetadataCompat(song: Song): MediaMetadataCompat = with(song) {
        return MediaMetadataCompat.Builder()
            .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
            .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, title)
            .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, subtitle)
            .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, subtitle)
            .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaId)
            .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, songSource.getTypedValue())
            .putString(
                MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI,
                imageSource.getTypedValue()
            )
            .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, imageSource.getTypedValue())
            .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION, subtitle)
            .build()
    }

    fun addOnReadyListener(action: (isSuccessful: Boolean) -> Unit): Boolean {
        //add only if source not yet initialized/thrown error
        return if (sourceState == SourceState.CREATED || sourceState == SourceState.INITIALIZING) {
            onReadyListeners += action
            false
        } else {
            action(sourceState == SourceState.INITIALIZED)
            true
        }
    }
}