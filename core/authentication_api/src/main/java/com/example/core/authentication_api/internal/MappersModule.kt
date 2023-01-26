package com.example.core.authentication_api.internal

import com.example.common.mapper.Mapper
import com.example.core.authentication_api.api.mapper.ProfileMapper
import com.example.core.authentication_api.api.model.LoginUserResponse
import com.example.core.model.authentication.Profile
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {

    @Binds
    abstract fun provideProfileMapper(
        implementation: ProfileMapper
    ): Mapper<Profile, LoginUserResponse>

}