package com.justparokq.feature.music_player.ui.viewmodels

import android.support.v4.media.session.PlaybackStateCompat
import com.justparokq.feature.music_player.data.entities.Song
import com.justparokq.feature.music_player.exoplayer.MusicService
import com.justparokq.feature.music_player.exoplayer.isPlaying
import com.justparokq.feature.music_player.other.Resource

data class MainViewState(
    val showPlayerFullScreen: Boolean = false,
    val mediaItems: Resource<List<Song>> = Resource.Loading(null),
    val currentPlaybackPosition: Long = 0L,

    val isConnected: Resource<Boolean> = Resource.Loading(),
    val networkError: Resource<Boolean> = Resource.Loading(),
    val playbackState: PlaybackStateCompat? = null,
    val currentPlayingSong: Song? = null,
) {
    val isSongPlaying: Boolean
        get() = playbackState?.isPlaying ?: false

    val currentSongDuration: Long
        get() = MusicService.currentSongDuration
}