package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.data_source.entity.db.BeerListItem
import com.example.meditationcomposeapp.data_source.entity.network.UpdateDescriptionResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.data_source.mappers.db.BeerDBMapper
import com.example.meditationcomposeapp.data_source.mappers.network.profile.UpdateDescriptionMapper
import com.example.meditationcomposeapp.data_source.mappers.network.punk.*
import com.example.meditationcomposeapp.model.entity.beer.*
import com.example.meditationcomposeapp.model.entity.login_flow.UpdateDescriptionModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {

    @Binds
    abstract fun provideUpdateDescriptionMapper(
        implementation: UpdateDescriptionMapper
    ): Mapper<UpdateDescriptionModel, UpdateDescriptionResponse>


    @Binds
    abstract fun provideBeerDBMapper(
        implementation: BeerDBMapper
    ): Mapper<BeerListItem, Beer>


}