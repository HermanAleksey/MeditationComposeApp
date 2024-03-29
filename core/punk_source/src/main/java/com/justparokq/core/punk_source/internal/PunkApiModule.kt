package com.justparokq.core.punk_source.internal

import com.justparokq.core.punk_source.BuildConfig
import com.justparokq.core.punk_source.api.Qualifiers
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
object PunkApiModule {

    @Provides
    @Singleton
    fun providePunkApi(
        @Qualifiers.PunkRetrofit retrofit: Retrofit
    ): PunkApi = retrofit.create(PunkApi::class.java)

    @Provides
    @Singleton
    @Qualifiers.PunkRetrofit
    fun provideRetrofitPunkApi(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.PUNK_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}