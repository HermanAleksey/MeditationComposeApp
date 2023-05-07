package com.example.feature.music_player.data.remote

import com.example.feature.music_player.data.entities.Song

interface MusicSource {

    suspend fun getMusic(): List<Song>
}