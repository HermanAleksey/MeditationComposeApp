package com.example.core.updates_history

import com.example.common.mapper.Mapper
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.core.updates_history.mapper.UpdateDescriptionResponseMapper
import com.example.core.updates_history.model.UpdateDescriptionResponse
import com.example.database.model.UpdateDescriptionDBEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {

    @Binds
    abstract fun provideUpdateDescriptionMapper(
        implementation: UpdateDescriptionResponseMapper
    ): Mapper<UpdateDescriptionModel, UpdateDescriptionResponse>

    @Binds
    abstract fun provideUpdateDescriptionDBMapper(
        implementation: UpdateDescriptionResponseMapper
    ): Mapper<UpdateDescriptionModel, UpdateDescriptionDBEntity>
}