package com.example.feature.music_player.ui.viewmodels


import android.support.v4.media.MediaBrowserCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.music_player.data.entities.Song
import com.example.feature.music_player.data.parser.SongParser
import com.example.feature.music_player.exoplayer.MusicService
import com.example.feature.music_player.exoplayer.MusicServiceConnection
import com.example.feature.music_player.exoplayer.currentPlaybackPosition
import com.example.feature.music_player.exoplayer.isPlayEnabled
import com.example.feature.music_player.exoplayer.isPlaying
import com.example.feature.music_player.exoplayer.isPrepared
import com.example.feature.music_player.extensions.combine
import com.example.feature.music_player.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection,
    private val songParser: SongParser,
) : ViewModel() {

    private val _mediaItems = MutableStateFlow<Resource<List<Song>>>(Resource.Loading(null))
    private val _isFullScreen = MutableStateFlow(false)
    private val _currentPlaybackPosition = MutableStateFlow(0L)

    val uiState: StateFlow<MainViewState> = combine(
        _isFullScreen,
        _mediaItems,
        _currentPlaybackPosition,
        musicServiceConnection.isConnected,
        musicServiceConnection.networkFailure,
        musicServiceConnection.playbackState,
        musicServiceConnection.currentPlayingSong,
    ) {
            isFullScreen, mediaItems, currentPlaybackPosition,
            isConnected, isNetworkFailure, playbackState, currentPLayingSong,
        ->

        val song = currentPLayingSong?.let { metadata ->
            songParser.parse(metadata)
        }
        MainViewState(
            showPlayerFullScreen = isFullScreen,
            mediaItems = mediaItems,
            currentPlaybackPosition = currentPlaybackPosition,
            isConnected = isConnected,
            networkError = isNetworkFailure,
            playbackState = playbackState,
            currentPlayingSong = song
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        MainViewState()
    )

    init {
        musicServiceConnection.subscribe(
            MusicService.MEDIA_ROOT_ID,
            object : MediaBrowserCompat.SubscriptionCallback() {
                override fun onChildrenLoaded(
                    parentId: String,
                    children: MutableList<MediaBrowserCompat.MediaItem>,
                ) {
                    super.onChildrenLoaded(parentId, children)
                    val items = children.map {
                        songParser.parse(it)
                    }
                    _mediaItems.update {
                        Resource.Success(items)
                    }
                }
            }
        )
        viewModelScope.launch {
            updateCurrentPlaybackPosition()
        }
    }

    fun processAction(action: MusicAction) {
        when (action) {
            is MusicAction.LaunchSong -> {
                launchSong(action.song)
            }
            is MusicAction.ToggleSongPlayback -> {
                toggleSong(action.song)
            }
            MusicAction.NextTrack -> {
                skipToNextSong()
            }
            MusicAction.PreviousTrack -> {
                skipToPreviousSong()
            }
            MusicAction.Rewind -> {
                val currentPosition = uiState.value.playbackState?.currentPlaybackPosition
                val timeAfterRewind = currentPosition?.let {
                    if (it - TIME_TO_REWIND < 0) {
                        0
                    } else it - TIME_TO_REWIND
                }
                seekTo(pos = timeAfterRewind ?: 0)
            }
            MusicAction.FastForward -> {
                val currentPosition = uiState.value.playbackState?.currentPlaybackPosition
                seekTo(pos = currentPosition?.plus(TIME_TO_FAST_FORWARD) ?: 0)
            }
            is MusicAction.SetTime -> {
                seekTo(pos = action.timeMillis)
            }
            MusicAction.OpenFullScreenPlayer -> {
                _isFullScreen.update {
                    true
                }
            }
            MusicAction.CloseFullScreenPlayer -> {
                _isFullScreen.update {
                    false
                }
            }
        }
    }

    private fun skipToNextSong() {
        musicServiceConnection.transportController.skipToNext()
    }

    private fun skipToPreviousSong() {
        musicServiceConnection.transportController.skipToPrevious()
    }

    private fun seekTo(pos: Long) {
        musicServiceConnection.transportController.seekTo(pos)
    }

    private fun toggleSong(song: Song) {
        val isPrepared = uiState.value.playbackState?.isPrepared ?: false
        if (isPrepared && song.mediaId == uiState.value.currentPlayingSong?.mediaId) {
            uiState.value.playbackState?.let { playbackState ->
                when {
                    playbackState.isPlaying -> {
                        musicServiceConnection.transportController.pause()
                    }
                    playbackState.isPlayEnabled -> {
                        musicServiceConnection.transportController.play()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun launchSong(song: Song) {
        musicServiceConnection.transportController.playFromMediaId(song.mediaId, null)
    }

    private suspend fun updateCurrentPlaybackPosition() {
        val currentPosition = uiState.value.playbackState?.currentPlaybackPosition
        if (currentPosition != null && currentPosition != uiState.value.currentPlaybackPosition) {
            _currentPlaybackPosition.update {
                currentPosition
            }
        }
        delay(UPDATE_PLAYER_POSITION_INTERVAL)
        updateCurrentPlaybackPosition()
    }

    override fun onCleared() {
        super.onCleared()
        musicServiceConnection.unsubscribe(
            MusicService.MEDIA_ROOT_ID,
            object : MediaBrowserCompat.SubscriptionCallback() {}
        )
    }

    companion object {
        private const val TIME_TO_REWIND = 10 * 1000
        private const val TIME_TO_FAST_FORWARD = 10 * 1000

        private const val UPDATE_PLAYER_POSITION_INTERVAL = 100L
    }
}
