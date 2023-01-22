package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.model.usecase.ExecuteDatabaseTransactionUseCase
import com.example.meditationcomposeapp.model.usecase.ExecuteDatabaseTransactionUseCaseImpl
import com.example.meditationcomposeapp.model.usecase.authentication.*
import com.example.meditationcomposeapp.model.usecase.punk.*
import com.example.meditationcomposeapp.model.usecase.punk.db.*
import com.example.meditationcomposeapp.model.usecase.punk.network.*
import com.example.meditationcomposeapp.model.usecase.remote_keys.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideGetAppUpdatesHistory(
        implementation: GetAppUpdatesHistoryUseCaseImpl
    ): GetAppUpdatesHistoryUseCase

    @Binds
    abstract fun provideLoginUseCase(
        implementation: LoginUseCaseImpl
    ): LoginUseCase

    @Binds
    abstract fun provideRegisterUseCase(
        implementation: RegisterUseCaseImpl
    ): RegisterUseCase

    @Binds
    abstract fun provideVerifyCodeUseCase(
        implementation: VerifyCodeUseCaseImpl
    ): VerifyCodeUseCase

    @Binds
    abstract fun provideRequestPasswordRestorationUseCase(
        implementation: RequestPasswordRestorationUseCaseImpl
    ): RequestPasswordRestorationUseCase

    @Binds
    abstract fun provideSetNewPasswordUseCase(
        implementation: SetNewPasswordUseCaseImpl
    ): SetNewPasswordUseCase

    @Binds
    abstract fun provideClearAuthDataUseCase(
        implementation: ClearAuthDataUseCaseImpl
    ): ClearAuthDataUseCase

    @Binds
    abstract fun provideGetBeersUseCase(
        implementation: GetBeersUseCaseImpl
    ): GetBeersUseCase

    @Binds
    abstract fun provideGetBeerByIdUseCase(
        implementation: GetBeerByIdUseCaseImpl
    ): GetBeerByIdUseCase

    @Binds
    abstract fun provideGetRandomBeerUseCase(
        implementation: GetRandomBeerUseCaseImpl
    ): GetRandomBeerUseCase

    @Binds
    abstract fun provideClearBeerDBUseCase(
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
    abstract fun provideClearRemoteKeysUseCase(
        implementation: ClearRemoteKeysUseCaseImpl
    ): ClearRemoteKeysUseCase

    @Binds
    abstract fun provideGetCreationTimeUseCase(
        implementation: GetCreationTimeUseCaseImpl
    ): GetCreationTimeUseCase

    @Binds
    abstract fun provideGetRemoteKeyBeBeerIdUseCase(
        implementation: GetRemoteKeyBeBeerIdUseCaseImpl
    ): GetRemoteKeyBeBeerIdUseCase

    @Binds
    abstract fun provideInsertAllRemoteKeysUseCase(
        implementation: InsertAllRemoteKeysUseCaseImpl
    ): InsertAllRemoteKeysUseCase

    @Binds
    abstract fun provideExecuteDatabaseTransactionUseCase(
        implementation: ExecuteDatabaseTransactionUseCaseImpl
    ): ExecuteDatabaseTransactionUseCase

    @Binds
    abstract fun provideGetBeerPagingRemoteMediatorUseCase(
        implementation: GetBeerPagingRemoteMediatorUseCaseImpl
    ): GetBeerPagingRemoteMediatorUseCase
}