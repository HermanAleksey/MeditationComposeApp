package com.example.punk_source.internal

import com.example.punk_source.api.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePunkRepository(
        implementation: PunkWebRepositoryImpl,
    ): PunkWebRepository

    @Binds
    abstract fun provideRemoteKeysRepository(
        implementation: RemoteKeysRepositoryImpl,
    ): RemoteKeysRepository

    @Binds
    abstract fun providePunkDBRepository(
        implementation: PunkDBRepositoryImpl,
    ): PunkDBRepository
}