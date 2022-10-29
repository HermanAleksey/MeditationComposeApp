package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepository
import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepositoryImpl
import com.example.meditationcomposeapp.data_source.repository.punk.PunkRepository
import com.example.meditationcomposeapp.data_source.repository.punk.PunkRepositoryImpl
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
    abstract fun provideUpdateDescriptionRepository(
        repository: UpdateDescriptionRepositoryImpl,
    ): UpdateDescriptionRepository

    @Binds
    abstract fun provideAuthenticationRepository(
        implementation: AuthenticationRepositoryImpl,
    ): AuthenticationRepository

    @Binds
    abstract fun providePunkRepository(
        implementation: PunkRepositoryImpl,
    ): PunkRepository
}