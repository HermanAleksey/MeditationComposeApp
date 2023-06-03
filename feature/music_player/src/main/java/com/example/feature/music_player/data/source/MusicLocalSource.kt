package com.example.feature.music_player.data.source

import com.example.feature.music_player.R
import com.example.feature.music_player.data.entities.LocalSong
import com.example.feature.music_player.data.entities.Song

class MusicLocalSource : MusicSource {

    override suspend fun getMusic(): List<Song> {
        //todo replace mocks
        return listOf(
            LocalSong(
                mediaId = "100",
                title = "Test1",
                subtitle = "subtitle",
                songResId = R.raw.sound_rain,
                imageResId = R.drawable.lovely_desert
            ),
            LocalSong(
                mediaId = "2",
                title = "Test2",
                subtitle = "subtitle",
                songResId = R.raw.fire_in_fireplace,
                imageResId = R.drawable.mountaineers
            ),
            LocalSong(
                mediaId = "3",
                title = "Test3",
                subtitle = "subtitle",
                songResId = R.raw.sound_rain,
                imageResId = R.drawable.painting_forest
            ),
            LocalSong(
                mediaId = "4",
                title = "Test4",
                subtitle = "subtitle",
                songResId = R.raw.sound_rain,
                imageResId = R.drawable.the_hill_sides
            ),
        ).map {
            it.toSong()
        }
    }
}