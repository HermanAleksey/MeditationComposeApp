package com.example.feature.music_player.exoplayer

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.example.feature.music_player.di.Local
import com.example.feature.music_player.exoplayer.callbacks.MusicPlaybackPrepared
import com.example.feature.music_player.exoplayer.callbacks.MusicPlayerEventListener
import com.example.feature.music_player.exoplayer.callbacks.MusicPlayerNotificationListener
import com.example.feature.music_player.exoplayer.music_source.MusicProvider
import com.example.feature.music_player.other.Constants.MEDIA_ROOT_ID
import com.example.feature.music_player.other.Constants.NETWORK_FAILURE
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.ext.mediasession.TimelineQueueNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MusicService : MediaBrowserServiceCompat() {

    @Inject
    lateinit var exoPlayer: ExoPlayer

    @Inject
    @Local
    lateinit var musicProvider: MusicProvider

    private val serviceScope = CoroutineScope(Dispatchers.Main + Job())

    private lateinit var mediaSessionCompat: MediaSessionCompat

    var isForegroundService = false

    private var currentPlayingSong: MediaMetadataCompat? = null

    private var isPlayerInitialize = false

    private lateinit var musicPlayerListener: MusicPlayerEventListener

    companion object {
        var currentSongDuration = 0L
            private set

        private const val SERVICE_TAG = "MusicService"
    }

    override fun onCreate() {
        super.onCreate()
        serviceScope.launch {
            musicProvider.fetchMediaData()
        }

        configureMediaSessionCompat()
        configureMediaSessionConnector()

        musicPlayerListener = MusicPlayerEventListener(this)
        exoPlayer.addListener(musicPlayerListener)

        showNotification()
    }

    private fun configureMediaSessionCompat() {
        val activityIntent = packageManager?.getLaunchIntentForPackage(packageName)?.let {
            PendingIntent.getActivity(this, 0, it, FLAG_IMMUTABLE)
        }
        mediaSessionCompat = MediaSessionCompat(this, SERVICE_TAG).apply {
            setSessionActivity(activityIntent)
            isActive = true
        }
        sessionToken = mediaSessionCompat.sessionToken
    }

    private fun configureMediaSessionConnector() {
        val musicPlaybackPreparer = MusicPlaybackPrepared(
            musicSource = musicProvider,
            onPlayerPrepared = {
                switchSong(mediaId = it)
            }
        )

        MediaSessionConnector(mediaSessionCompat).apply {
            setPlaybackPreparer(musicPlaybackPreparer)
            setQueueNavigator(MusicQueueNavigator())
            setPlayer(exoPlayer)
        }
    }

    private fun showNotification() {
        MusicNotificationManger(
            context = this,
            sessionToken = mediaSessionCompat.sessionToken,
            notificationListener = MusicPlayerNotificationListener(this),
            newSongCallback = {
                currentSongDuration = exoPlayer.duration
            }
        ).showNotification(exoPlayer)
    }

    private inner class MusicQueueNavigator : TimelineQueueNavigator(mediaSessionCompat) {
        override fun getMediaDescription(player: Player, windowIndex: Int): MediaDescriptionCompat {
            return musicProvider.mediaMetadataCompats[windowIndex].description
        }
    }

    //call when launch player with playlist for the first time
    private fun initialPreparePlayer(    ) {
        with(exoPlayer) {
            setMediaSource(musicProvider.getConcatenatedMediaSource())
            prepare()
            seekTo(0, 0L)
            playWhenReady = false
        }
    }

    private fun switchSong(
        mediaId: String,
    ) {
        musicProvider.mediaMetadataCompats.find {
            mediaId == it.description.mediaId
        }.let { media ->
            currentPlayingSong = media
            exoPlayer.seekTo(
                musicProvider.mediaMetadataCompats.indexOf(media),
                0L
            )
            exoPlayer.playWhenReady = true
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        exoPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        exoPlayer.removeListener(musicPlayerListener)
        exoPlayer.release()
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?,
    ): BrowserRoot {
        return BrowserRoot(MEDIA_ROOT_ID, null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>,
    ) {
        when (parentId) {
            MEDIA_ROOT_ID -> {
                val resultsSent = musicProvider.addOnReadyListener { isInitialized ->
                    if (isInitialized) {
                        val mediaItems = musicProvider.mediaMetadataCompats.map {
                            MediaBrowserCompat.MediaItem(
                                it.description,
                                MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
                            )
                        }.toMutableList()
                        result.sendResult(mediaItems)
                        if (!isPlayerInitialize && mediaItems.isNotEmpty()) {
                            initialPreparePlayer()
                            isPlayerInitialize = true
                        }
                    } else {
                        mediaSessionCompat.sendSessionEvent(NETWORK_FAILURE, null)
                        result.sendResult(null)
                    }
                }
                if (!resultsSent) {
                    result.detach()
                }
            }
        }
    }
}