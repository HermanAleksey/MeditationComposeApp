package com.justparokq.feature.chat.api.di

import com.justparokq.feature.chat.api.data.web_socket.ChatWebSocketListener
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.WebSocketListener

@Module
@InstallIn(SingletonComponent::class)
abstract class ChatBinderModule {

    @Binds
    abstract fun provideWebSocketListener(
        implementation: ChatWebSocketListener,
    ): WebSocketListener
}