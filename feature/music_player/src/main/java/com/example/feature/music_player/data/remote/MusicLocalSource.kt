package com.example.feature.music_player.data.remote

import com.example.feature.music_player.R
import com.example.feature.music_player.data.entities.LocalSong
import com.example.feature.music_player.data.entities.Song

class MusicLocalSource : MusicSource {

    override suspend fun getMusic(): List<Song> {
        //todo replace mock
        return listOf(
            LocalSong(
                mediaId = "1",
                title = "Test",
                subtitle = "subtitle",
                songResId = R.raw.sound_rain,
                imageResId = R.drawable.ic_music
            )
        ).map {
            it.toSong()
        }
    }
}