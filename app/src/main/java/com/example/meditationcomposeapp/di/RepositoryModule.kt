package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepository
import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthenticationRepository(
        implementation: AuthenticationRepositoryImpl
    ): AuthenticationRepository

}