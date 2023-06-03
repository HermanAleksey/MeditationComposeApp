package com.example.feature.music_player.exoplayer.music_source

import android.support.v4.media.MediaMetadataCompat
import com.example.feature.music_player.data.entities.DataSourceMapper
import com.example.feature.music_player.data.entities.LocalRes
import com.example.feature.music_player.data.entities.LocalURL
import com.example.feature.music_player.data.entities.WebURL
import com.example.feature.music_player.data.parsers.toMediaMetadataCompat
import com.example.feature.music_player.data.source.MusicSource
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MusicProvider(
    private val source: MusicSource,
    private val dataSourceFactory: DefaultDataSource.Factory,
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
            val songDataSource = metadataCompat.description.mediaUri?.toString()?.let {
                DataSourceMapper().getDataSourceType(it)
            } ?: throw Exception("Can't cast URI to data source")

            val mediaSource = when (songDataSource) {
                is LocalRes -> {
                    val rawResId = songDataSource.resId
                    parseLocalSongToMediaSource(rawResId)
                }
                is WebURL -> {
                    val url = songDataSource.url
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
            song.toMediaMetadataCompat()
        }

        withContext(Dispatchers.Main) {
            sourceState = SourceState.INITIALIZED
        }
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