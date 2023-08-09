package com.justparokq.feature.music_player.data.provider

import com.justparokq.feature.music_player.data.entities.Song
import com.justparokq.feature.music_player.data.entities.WebSong
import com.justparokq.feature.music_player.data.parser.SongParser

/**
 * Currently web source is not working, but logic of passing
 * urls for images and songs is done.
 *
 * Need to create web source that will provide urls
 * */
class MusicWebProvider(
    private val songParser: SongParser,
) : MusicProvider {

    override suspend fun getMusic(): List<Song> {
        return listOf(
            WebSong(
                mediaId = "1",
                title = "Met You",
                subtitle = "if found",
                songUrl = "https://firebasestorage.googleapis.com/v0/b/musicplayer-2672e.appspot.com/o/if%20found%20-%20Met%20You%20%5BNCS%20Release%5D.mp3?alt=media&token=d3d487ae-5894-4430-a19a-24aeb0c2a520",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/musicplayer-2672e.appspot.com/o/Met%20you.jpg?alt=media&token=ab5c00ea-86e7-4f70-a7bf-f7a79b93d6bd"
            ),
            WebSong(
                mediaId = "2",
                title = "Not me",
                subtitle = "yes do",
                songUrl = "https://firebasestorage.googleapis.com/v0/b/musicplayer-2672e.appspot.com/o/Cartoon%20x%20nublu%20x%20Gameboy%20Tetris%20-%20Biology%20%5BNCS%20Release%5D.mp3?alt=media&token=acb1b206-c8ad-4405-9ee4-ec50bdae2309",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/musicplayer-2672e.appspot.com/o/Biology.jpg?alt=media&token=21322f8d-26e3-4983-97a4-2f911a4d0007"
            ),
            WebSong(
                mediaId = "3",
                title = "Feelings",
                subtitle = "Diviners & Azertion",
                songUrl = "https://firebasestorage.googleapis.com/v0/b/musicplayer-2672e.appspot.com/o/Diviners%20%26%20Azertion%20-%20Feelings%20%5BNCS%20Release%5D.mp3?alt=media&token=82ae7542-4dbd-4dd7-9a6d-876bc3d573e7",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/musicplayer-2672e.appspot.com/o/Feelings.jpg?alt=media&token=e1ee4b85-8994-49d6-9d2e-8b651e0918e3"
            ),
            WebSong(
                mediaId = "4",
                title = "Feelings",
                subtitle = "Diviners & Azertion",
                songUrl = "https://firebasestorage.googleapis.com/v0/b/musicplayer-2672e.appspot.com/o/Kisma%20-%20Fly%20Again%20%5BNCS%20Release%5D.mp3?alt=media&token=77cd5ddd-d914-4034-ae34-0d0d714de62b",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/musicplayer-2672e.appspot.com/o/Fly%20Again.jpg?alt=media&token=1c3c4c33-ce7a-4c0f-a7d2-1ec8791a298b"
            ),
            WebSong(
                mediaId = "5",
                title = "From server",
                subtitle = "Diviners & Azertion",
                songUrl = "http://0.0.0.0:8580/song/stream",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/musicplayer-2672e.appspot.com/o/Biology.jpg?alt=media&token=21322f8d-26e3-4983-97a4-2f911a4d0007"
            )
        ).map {
            songParser.parse(it)
        }
    }
}
