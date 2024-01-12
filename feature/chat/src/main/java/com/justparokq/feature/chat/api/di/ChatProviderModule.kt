package com.justparokq.feature.chat.api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object ChatProviderModule {

    @Singleton
    @Provides
    fun provideOkHttp() = OkHttpClient()

}