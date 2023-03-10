package com.example.core.authentication_source.internal

import com.example.core.authentication_api.BuildConfig
import com.example.core.authentication_source.api.AuthenticationApi
import com.example.core.authentication_source.api.Qualifiers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationSourceModule {

    @Provides
    @Singleton
    fun provideAuthenticationApi(
        @Qualifiers.AuthRetrofit retrofit: Retrofit
    ): AuthenticationApi = retrofit.create(AuthenticationApi::class.java)

    @Provides
    @Singleton
    @Qualifiers.AuthRetrofit
    fun provideRetrofitAuth(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTHENTICATION_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
}