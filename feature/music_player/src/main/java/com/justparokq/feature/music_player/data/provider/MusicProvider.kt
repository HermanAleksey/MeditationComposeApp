package com.justparokq.feature.music_player.data.provider

import com.justparokq.feature.music_player.data.entities.Song

interface MusicProvider {

    suspend fun getMusic(): List<Song>
}