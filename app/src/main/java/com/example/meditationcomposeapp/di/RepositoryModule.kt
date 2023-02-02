package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.data_source.data_store.UserDataStoreImpl
import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepository
import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepositoryImpl
import com.example.meditationcomposeapp.data_source.repository.punk.*
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideDataStore(
        implementation: UserDataStoreImpl,
    ): UserDataStore

    @Binds
    abstract fun provideUpdateDescriptionRepository(
        implementation: UpdateDescriptionRepositoryImpl,
    ): UpdateDescriptionRepository

    @Binds
    abstract fun providePunkRepository(
        implementation: PunkRepositoryImpl,
    ): PunkRepository

    @Binds
    abstract fun provideRemoteKeysRepository(
        implementation: RemoteKeysRepositoryImpl,
    ): RemoteKeysRepository

    @Binds
    abstract fun providePunkDBRepository(
        implementation: PunkDBRepositoryImpl,
    ): PunkDBRepository
}