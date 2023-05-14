package com.example.musicplayer.data.remote

import com.example.musicplayer.data.entities.Song

interface MusicSource {

    suspend fun getMusic(): List<Song>
}