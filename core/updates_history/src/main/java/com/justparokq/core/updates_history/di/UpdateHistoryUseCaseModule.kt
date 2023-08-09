package com.justparokq.core.updates_history.di

import com.justparokq.core.updates_history.use_case.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UpdateHistoryUseCaseModule {

    @Binds
    abstract fun provideGetAllUpdatesDescriptionsUseCase(
        implementation: GetAllUpdatesDescriptionsUseCaseImpl
    ): GetAllUpdatesDescriptionsUseCase

    @Binds
    abstract fun provideGetLastUpdateDescriptionUseCase(
        implementation: GetLastUpdateDescriptionUseCaseImpl
    ): GetLastUpdateDescriptionUseCase

    @Binds
    abstract fun provideInsertAllUpdatesDescriptionsUseCase(
        implementation: InsertAllUpdatesDescriptionsUseCaseImpl
    ): InsertAllUpdatesDescriptionsUseCase

    @Binds
    abstract fun provideGetAppUpdatesHistoryUseCase(
        implementation: GetAppUpdatesHistoryUseCaseImpl
    ): GetAppUpdatesHistoryUseCase

}