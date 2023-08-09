package com.justparokq.core.punk_source.internal

import com.justparokq.core.punk_source.api.repository.*
import com.justparokq.core.punk_source.api.repository.PunkWebRepositoryImpl
import com.justparokq.core.punk_source.api.repository.RemoteKeysRepositoryImpl
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