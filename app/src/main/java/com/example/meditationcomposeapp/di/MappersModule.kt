package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.entity.LoginUserResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.data_source.mappers.profile.ProfileMapper
import com.example.meditationcomposeapp.data_source.mappers.profile.SleepStatisticMapper
import com.example.meditationcomposeapp.data_source.mappers.random_api.BeerMapper
import com.example.meditationcomposeapp.model.entity.Beer
import com.example.meditationcomposeapp.model.entity.Profile
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

    @Binds
    abstract fun provideBeerMapper(
        implementation: BeerMapper
    ): Mapper<Beer, BeerResponse>

    @Binds
    abstract fun provideSleepStatisticMapper(
        implementation: SleepStatisticMapper
    ): Mapper<Profile.SleepDetails, LoginUserResponse.SleepDetailsResponse>
}