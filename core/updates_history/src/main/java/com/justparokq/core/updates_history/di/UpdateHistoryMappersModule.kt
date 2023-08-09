package com.justparokq.core.updates_history.di

import com.justparokq.core.common.mapper.BidirectionalMapper
import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.database.model.UpdateDescriptionDBEntity
import com.justparokq.core.model.updates.UpdateDescriptionModel
import com.justparokq.core.updates_history.mapper.UpdateDescriptionDBMapper
import com.justparokq.core.updates_history.mapper.UpdateDescriptionResponseMapper
import com.justparokq.core.updates_history.model.UpdateDescriptionResponse
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