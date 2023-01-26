package com.example.network

import com.example.core.network.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor {
    fun create(): HttpLoggingInterceptor {
        val level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply { httpLoggingInterceptor.level = level }
    }
}