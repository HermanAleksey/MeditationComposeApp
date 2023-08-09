package com.justparokq.feature.music_player.di

import android.content.Context
import com.justparokq.feature.music_player.data.parser.SongParser
import com.justparokq.feature.music_player.data.provider.MusicLocalProvider
import com.justparokq.feature.music_player.data.provider.MusicWebProvider
import com.justparokq.feature.music_player.exoplayer.MusicServiceConnection
import com.justparokq.feature.music_player.exoplayer.music_source.MusicSource
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
    fun provideMusicDatabase(
        songParser: SongParser,
    ): com.justparokq.feature.music_player.data.provider.MusicProvider = MusicWebProvider(songParser)

    @ServiceScoped
    @Provides
    @Local
    fun provideLocalMusicDatabase(
        songParser: SongParser,
    ): com.justparokq.feature.music_player.data.provider.MusicProvider = MusicLocalProvider(songParser)

    @ServiceScoped
    @Provides
    fun provideSongParser(): SongParser = SongParser()

    @ServiceScoped
    @Provides
    fun provideMusicServiceConnection(
        @ApplicationContext context: Context,
    ): MusicServiceConnection = MusicServiceConnection(context)


    @ServiceScoped
    @Provides
    @Local
    fun provideLocalMusicSource(
        @Local musicProvider: com.justparokq.feature.music_player.data.provider.MusicProvider,
        factory: DefaultDataSource.Factory,
        songParser: SongParser,
    ): MusicSource = MusicSource(
        musicProvider, factory, songParser
    )

    @ServiceScoped
    @Provides
    @Web
    fun provideWebMusicSource(
        @Web musicProvider: com.justparokq.feature.music_player.data.provider.MusicProvider,
        factory: DefaultDataSource.Factory,
        songParser: SongParser,
    ): MusicSource = MusicSource(
        musicProvider, factory, songParser
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
