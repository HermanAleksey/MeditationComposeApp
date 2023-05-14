package com.example.feature.music_player.exoplayer

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.example.feature.music_player.other.Constants.NETWORK_FAILURE
import com.example.feature.music_player.other.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Class create instance of MediaPlayer and provide control over playback
 * and state of it
 * */
class MusicServiceConnection(
    context: Context
) {
    private val _isConnected =
        MutableStateFlow<Resource<Boolean>>(Resource.Loading(null))
    val isConnected = _isConnected.asStateFlow()

    private val _networkFailure =
        MutableStateFlow<Resource<Boolean>>(Resource.Loading(null))
    val networkFailure = _networkFailure.asStateFlow()

    private val _playbackState = MutableStateFlow<PlaybackStateCompat?>(null)
    val playbackState = _playbackState.asStateFlow()

    /**
     * The most recent song played during this session
     * */
    private val _currentPlayingSong = MutableStateFlow<MediaMetadataCompat?>(null)
    var currentPlayingSong = _currentPlayingSong.asStateFlow()

    /**
     * Provide control API over playback
     * */
    private lateinit var mediaController: MediaControllerCompat
    val transportController: MediaControllerCompat.TransportControls
        get() = mediaController.transportControls

    private val mediaBrowserConnectionCallback = MediaBrowserConnectionCallback(context)
    private val mediaBrowser = MediaBrowserCompat(
        context,
        ComponentName(
            context,
            MusicService::class.java
        ),
        mediaBrowserConnectionCallback,
        null
    ).apply { connect() }

    fun subscribe(parentId: String, callback: MediaBrowserCompat.SubscriptionCallback) {
        mediaBrowser.subscribe(parentId, callback)
    }

    fun unsubscribe(parentId: String, callback: MediaBrowserCompat.SubscriptionCallback) {
        mediaBrowser.unsubscribe(parentId, callback)
    }

    private inner class MediaBrowserConnectionCallback(
        private val context: Context
    ) : MediaBrowserCompat.ConnectionCallback() {

        override fun onConnected() {
            mediaController = MediaControllerCompat(context, mediaBrowser.sessionToken).apply {
                registerCallback(MediaControllerCallback())
            }
            _isConnected.update {
                Resource.Success(true)
            }
        }

        override fun onConnectionSuspended() {
            _isConnected.update {
                Resource.Error(
                    "The connection was suspended",
                    false
                )
            }
        }

        override fun onConnectionFailed() {
            _isConnected.update {
                Resource.Error(
                    "Couldn't connect to media browser",
                    false
                )
            }
        }
    }

    private inner class MediaControllerCallback : MediaControllerCompat.Callback() {

        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
            _playbackState.update { state }
        }

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
            super.onMetadataChanged(metadata)
            _currentPlayingSong.update { metadata }
        }

        override fun onSessionEvent(event: String?, extras: Bundle?) {
            super.onSessionEvent(event, extras)
            when (event) {
                NETWORK_FAILURE -> _networkFailure.update {
                    Resource.Error(
                        "Couldn't connect to the server. Please check your internet connection.",
                        null
                    )
                }
            }
        }

        override fun onSessionDestroyed() {
            mediaBrowserConnectionCallback.onConnectionSuspended()
        }
    }
}