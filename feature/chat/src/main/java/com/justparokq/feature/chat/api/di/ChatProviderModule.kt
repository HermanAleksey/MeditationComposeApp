package com.justparokq.feature.chat.api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatProviderModule {

    @Singleton
    @Provides
    @Chat
    fun provideChatOkHttp() = OkHttpClient()

}