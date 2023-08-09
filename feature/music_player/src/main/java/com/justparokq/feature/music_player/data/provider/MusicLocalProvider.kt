package com.justparokq.feature.music_player.data.provider

import com.justparokq.feature.music_player.R
import com.justparokq.feature.music_player.data.entities.LocalSong
import com.justparokq.feature.music_player.data.entities.Song
import com.justparokq.feature.music_player.data.parser.SongParser

class MusicLocalProvider(
    private val songParser: SongParser,
) : MusicProvider {

    override suspend fun getMusic(): List<Song> {
        return listOf(
            LocalSong(
                mediaId = "1",
                title = "Fire camp",
                subtitle = "With cacao",
                songResId = R.raw.fire_in_fireplace,
                imageResId = R.drawable.lovely_desert
            ),
            LocalSong(
                mediaId = "2",
                title = "Zikadens",
                subtitle = "In the evening",
                songResId = R.raw.zikadens,
                imageResId = R.drawable.mountaineers
            ),
            LocalSong(
                mediaId = "3",
                title = "Rain",
                subtitle = "Tropical",
                songResId = R.raw.sound_rain,
                imageResId = R.drawable.painting_forest
            ),
            LocalSong(
                mediaId = "4",
                title = "Clouds",
                subtitle = "And wind",
                songResId = R.raw.clouds_n_wind,
                imageResId = R.drawable.the_hill_sides
            ),
        ).map {
            songParser.parse(it)
        }
    }
}