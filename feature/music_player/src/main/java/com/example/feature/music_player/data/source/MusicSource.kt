package com.example.feature.music_player.data.source

import com.example.feature.music_player.data.entities.Song

interface MusicSource {

    suspend fun getMusic(): List<Song>
}