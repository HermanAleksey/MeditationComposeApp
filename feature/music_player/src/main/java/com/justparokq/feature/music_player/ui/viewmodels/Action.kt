package com.justparokq.feature.music_player.ui.viewmodels

import com.justparokq.feature.music_player.data.entities.Song

sealed interface MusicAction {

    data class LaunchSong(
        val song: Song
    ): MusicAction

    object NextTrack: MusicAction

    object PreviousTrack: MusicAction

    data class SetTime(
        val timeMillis: Long
    ): MusicAction

    object FastForward: MusicAction

    object Rewind: MusicAction

    data class ToggleSongPlayback(
        val song: Song
    ): MusicAction

    object OpenFullScreenPlayer: MusicAction

    object CloseFullScreenPlayer: MusicAction
}