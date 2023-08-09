package com.justparokq.core.punk_source.internal

import com.justparokq.core.punk_source.api.use_case.punk.GetBeerPagingRemoteMediatorUseCase
import com.justparokq.core.punk_source.api.use_case.punk.GetBeerPagingRemoteMediatorUseCaseImpl
import com.justparokq.core.punk_source.api.use_case.punk.db.*
import com.justparokq.core.punk_source.api.use_case.punk.network.*
import com.justparokq.core.punk_source.api.use_case.remote_keys.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PunkUseCaseModule {

    @Binds
    abstract fun provideClearBeersDBUseCase(
        implementation: ClearBeersDBUseCaseImpl
    ): ClearBeersDBUseCase

    @Binds
    abstract fun provideGetBeerPagingSourceUseCase(
        implementation: GetBeerPagingSourceUseCaseImpl
    ): GetBeerPagingSourceUseCase

    @Binds
    abstract fun provideInsertBeersIntoDBUseCase(
        implementation: InsertBeersIntoDBUseCaseImpl
    ): InsertBeersIntoDBUseCase

    @Binds
    abstract fun provideGetBeerByIdUseCase(
        implementation: GetBeerByIdUseCaseImpl
    ): GetBeerByIdUseCase

    @Binds
    abstract fun provideGetBeerUseCase(
        implementation: GetBeersUseCaseImpl
    ): GetBeersUseCase

    @Binds
    abstract fun provideGetRandomBeerUseCase(
        implementation: GetRandomBeerUseCaseImpl
    ): GetRandomBeerUseCase

    @Binds
    abstract fun provideGetBeerPagingRemoteMediatorUseCase(
        implementation: GetBeerPagingRemoteMediatorUseCaseImpl
    ): GetBeerPagingRemoteMediatorUseCase

    @Binds
    abstract fun provideClearRemoteKeysUseCase(
        implementation: ClearRemoteKeysUseCaseImpl
    ): ClearRemoteKeysUseCase

    @Binds
    abstract fun provideGetCreationTimeUseCase(
        implementation: GetCreationTimeUseCaseImpl
    ): GetCreationTimeUseCase


    @Binds
    abstract fun provideGetRemoteKeyByBeerIdUseCase(
        implementation: GetRemoteKeyByBeerIdUseCaseImpl
    ): GetRemoteKeyByBeerIdUseCase

    @Binds
    abstract fun provideInsertAllRemoteKeysUseCase(
        implementation: InsertAllRemoteKeysUseCaseImpl
    ): InsertAllRemoteKeysUseCase
}