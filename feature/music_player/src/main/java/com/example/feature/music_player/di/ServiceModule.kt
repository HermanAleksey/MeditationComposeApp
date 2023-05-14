package com.example.feature.music_player.di

import android.content.Context
import com.example.feature.music_player.data.source.MusicLocalSource
import com.example.feature.music_player.data.source.MusicSource
import com.example.feature.music_player.data.source.MusicWebSource
import com.example.feature.music_player.exoplayer.music_source.MusicProvider
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.upstream.DefaultDataSource
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
        factory: DefaultDataSource.Factory,
    ): MusicProvider = MusicProvider(
        musicSource, factory
    )

    @ServiceScoped
    @Provides
    @Web
    fun provideWebMusicSource(
        @Web musicSource: MusicSource,
        factory: DefaultDataSource.Factory,
    ): MusicProvider = MusicProvider(
        musicSource, factory
    )

    @ServiceScoped
    @Provides
    fun provideAudioAttributes(): AudioAttributes {
        return AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()
    }

    @ServiceScoped
    @Provides
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes,
    ) = ExoPlayer.Builder(context).build().apply {
        setAudioAttributes(audioAttributes, true)
        setHandleAudioBecomingNoisy(true)
    }

    @ServiceScoped
    @Provides
    fun provideDateSourceFactory(
        @ApplicationContext context: Context,
    ) = DefaultDataSource.Factory(context)
}
