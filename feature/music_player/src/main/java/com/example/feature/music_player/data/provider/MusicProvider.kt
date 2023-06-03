package com.example.feature.music_player.data.provider

import com.example.feature.music_player.data.entities.Song

interface MusicProvider {

    suspend fun getMusic(): List<Song>
}