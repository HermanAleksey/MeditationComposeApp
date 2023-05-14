package com.example.musicplayer.di

import android.content.Context
import com.example.feature.music_player.data.remote.MusicWebSource
import com.example.musicplayer.data.remote.MusicLocalSource
import com.example.musicplayer.data.remote.MusicSource
import com.example.musicplayer.exoplayer.music_source.MusicProvider
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Web

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Local

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    @Web
    fun provideMusicDatabase(): MusicSource = MusicWebSource()

    @ServiceScoped
    @Provides
    @Local
    fun provideLocalMusicDatabase(): MusicSource = MusicLocalSource()

    @ServiceScoped
    @Provides
    @Local
    fun provideLocalMusicSource(
        @Local musicSource: MusicSource,
        factory: DefaultDataSourceFactory,
    ): MusicProvider = MusicProvider(
        musicSource, factory
    )

    @ServiceScoped
    @Provides
    @Web
    fun provideWebMusicSource(
        @Web musicSource: MusicSource,
        factory: DefaultDataSourceFactory,
    ): MusicProvider = MusicProvider(
        musicSource, factory
    )

    @ServiceScoped
    @Provides
    fun provideAudioAttributes(): AudioAttributes = AudioAttributes.Builder()
        .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    @ServiceScoped
    @Provides
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes
    ) = SimpleExoPlayer.Builder(context).build().apply {
        setAudioAttributes(audioAttributes, true)
        setHandleAudioBecomingNoisy(true)
    }

    @ServiceScoped
    @Provides
    fun provideDateSourceFactory(
        @ApplicationContext context: Context
    ) = DefaultDataSourceFactory(context, Util.getUserAgent(context, APP_NAME))
}

const val APP_NAME = "Music App"