package com.justparokq.feature.chat.api.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.justparokq.feature.chat.api.data.desirealizer.WSDataDeserializer
import com.justparokq.feature.chat.api.data.model.ChatWSDataDto
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

    @Singleton
    @Provides
    @Chat
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(ChatWSDataDto::class.java, WSDataDeserializer())
            .create()
    }
}