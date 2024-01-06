package com.justparokq.feature.music_player.exoplayer

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.ext.mediasession.TimelineQueueNavigator
import com.justparokq.feature.music_player.data.entities.DataSourceMapper
import com.justparokq.feature.music_player.di.Local
import com.justparokq.feature.music_player.exoplayer.callbacks.MusicPlaybackPreparer
import com.justparokq.feature.music_player.exoplayer.callbacks.MusicPlayerEventListener
import com.justparokq.feature.music_player.exoplayer.callbacks.MusicPlayerNotificationListener
import com.justparokq.feature.music_player.exoplayer.music_source.MusicSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MusicService : MediaBrowserServiceCompat(),
    MediaSessionConnector.PlaybackPreparer by MusicPlaybackPreparer() {

    @Inject
    lateinit var exoPlayer: ExoPlayer

    @Inject
    @Local
    lateinit var musicSource: MusicSource

    @Inject
    lateinit var dataSourceMapper: DataSourceMapper

    private val serviceScope = CoroutineScope(Dispatchers.Main + Job())

    private lateinit var mediaSessionCompat: MediaSessionCompat

    var isForegroundService = false

    private var currentPlayingSong: MediaMetadataCompat? = null

    private var isPlayerInitialize = false

    private var musicPlayerListener: MusicPlayerEventListener = MusicPlayerEventListener(this)

    private val playerNotificationListener = MusicPlayerNotificationListener(this)

    override fun onCreate() {
        super.onCreate()
        serviceScope.launch {
            musicSource.fetchMediaData()
        }

        configureMediaSessionCompat()
        configureMediaSessionConnector()

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
        MediaSessionConnector(mediaSessionCompat).apply {
            setPlaybackPreparer(this@MusicService)
            setQueueNavigator(MusicQueueNavigator())
            setPlayer(exoPlayer)
        }
    }

    override fun onPrepareFromMediaId(mediaId: String, playWhenReady: Boolean, extras: Bundle?) {
        musicSource.addOnReadyListener {
            switchSong(mediaId)
        }
    }

    private fun showNotification() {
        MusicNotificationManger(
            context = this,
            sessionToken = mediaSessionCompat.sessionToken,
            notificationListener = playerNotificationListener,
            onNewSongStarted = {
                currentSongDuration = exoPlayer.duration
            },
            dataSourceMapper = dataSourceMapper,
        ).showNotification(exoPlayer)
    }

    private inner class MusicQueueNavigator : TimelineQueueNavigator(mediaSessionCompat) {
        override fun getMediaDescription(player: Player, windowIndex: Int): MediaDescriptionCompat {
            return musicSource.mediaMetadataCompats[windowIndex].description
        }
    }

    //call when launch player with playlist for the first time
    private fun initialPreparePlayer() {
        with(exoPlayer) {
            setMediaSource(musicSource.getConcatenatedMediaSource())
            prepare()
            seekTo(0, 0L)
            playWhenReady = false
        }
    }

    private fun switchSong(
        mediaId: String,
    ) {
        musicSource.mediaMetadataCompats.find {
            mediaId == it.description.mediaId
        }.let { media ->
            currentPlayingSong = media
            exoPlayer.seekTo(
                musicSource.mediaMetadataCompats.indexOf(media),
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
                val resultsSent = musicSource.addOnReadyListener { isInitialized ->
                    if (isInitialized) {
                        val mediaItems = musicSource.mediaMetadataCompats.map {
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
                        mediaSessionCompat.sendSessionEvent(NETWORK_ERROR, null)
                        result.sendResult(null)
                    }
                }
                if (!resultsSent) {
                    result.detach()
                }
            }
        }
    }

    companion object {
        var currentSongDuration = 0L
            private set

        private const val SERVICE_TAG = "MusicService"
        const val NETWORK_ERROR = "NETWORK_ERROR"
        const val MEDIA_ROOT_ID = "MEDIA_ROOT_ID"
    }
}