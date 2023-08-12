package com.justparokq.feature.charts.api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request

@Module
@InstallIn(SingletonComponent::class)
object ChartsModule {

    @Provides
    fun provideRequest(): Request {
        return Request.Builder()
            .url("ws://0.0.0.0:8580/chartData")
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}