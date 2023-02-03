package com.example.core.updates_history

import com.example.common.mapper.Mapper
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.core.updates_history.mapper.UpdateDescriptionDBMapper
import com.example.core.updates_history.mapper.UpdateDescriptionResponseMapper
import com.example.core.updates_history.model.UpdateDescriptionResponse
import com.example.database.model.UpdateDescriptionDBEntity
import dagger.Binds
import dagger.Module

@Module
abstract class UpdateHistoryMappersModule {

    @Binds
    abstract fun provideUpdateDescriptionWebMapper(
        implementation: UpdateDescriptionResponseMapper
    ): Mapper<UpdateDescriptionModel, UpdateDescriptionResponse>

    @Binds
    abstract fun provideUpdateDescriptionDBMapper(
        implementation: UpdateDescriptionDBMapper
    ): Mapper<UpdateDescriptionModel, UpdateDescriptionDBEntity>
}