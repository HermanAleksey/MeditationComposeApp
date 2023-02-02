package com.example.core.authentication_source.internal

import com.example.common.mapper.Mapper
import com.example.core.authentication_source.api.mapper.ProfileMapper
import com.example.core.authentication_source.api.model.LoginUserResponse
import com.example.core.authentication_source.api.repository.AuthenticationRepository
import com.example.core.authentication_source.api.repository.AuthenticationRepositoryImpl
import com.example.core.model.authentication.Profile
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationBindsModule {

    @Binds
    abstract fun provideProfileMapper(
        implementation: ProfileMapper
    ): Mapper<Profile, LoginUserResponse>

    @Binds
    abstract fun provideAuthenticationRepository(
        implementation: AuthenticationRepositoryImpl,
    ): AuthenticationRepository

}