package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.BuildConfig
import com.example.meditationcomposeapp.data_source.network.AuthenticationApi
import com.example.punk_api.internal.PunkApi
import com.example.meditationcomposeapp.data_source.utils.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthenticationApi(
        @Qualifiers.AuthRetrofit   retrofit: Retrofit
    ): AuthenticationApi = retrofit.create(AuthenticationApi::class.java)

    @Provides
    @Singleton
    @Qualifiers.AuthRetrofit
    fun provideRetrofitAuth(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClientPlus(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = LoggingInterceptor().create()
}