package com.example.core.updates_history.di

import com.example.common.mapper.BidirectionalMapper
import com.example.common.mapper.Mapper
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.core.updates_history.mapper.UpdateDescriptionDBMapper
import com.example.core.updates_history.mapper.UpdateDescriptionResponseMapper
import com.example.core.updates_history.model.UpdateDescriptionResponse
import com.example.database.model.UpdateDescriptionDBEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UpdateHistoryMappersModule {

    @Binds
    abstract fun provideUpdateDescriptionWebMapper(
        implementation: UpdateDescriptionResponseMapper
    ): Mapper<UpdateDescriptionModel, UpdateDescriptionResponse>

    @Binds
    abstract fun provideUpdateDescriptionDBBiMapper(
        implementation: UpdateDescriptionDBMapper
    ): BidirectionalMapper<UpdateDescriptionModel, UpdateDescriptionDBEntity>
}