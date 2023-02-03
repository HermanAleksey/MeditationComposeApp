package com.example.core.updates_history

import com.example.common.mapper.Mapper
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.core.updates_history.source.db.UpdateDescriptionDBRepository
import com.example.core.updates_history.source.db.UpdateDescriptionDBRepositoryImpl
import com.example.core.updates_history.source.web.UpdateDescriptionWebRepository
import com.example.core.updates_history.source.web.UpdateDescriptionWebRepositoryImpl
import com.example.database.dao.UpdateDescriptionDao
import com.example.database.model.UpdateDescriptionDBEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HistoryDescriptionSourceModule {

    @Provides
    @Singleton
    fun provideUpdateDescriptionWebRepository(
        updatesHistoryApi: UpdatesHistoryApi
    ): UpdateDescriptionWebRepository = UpdateDescriptionWebRepositoryImpl(
        updatesHistoryApi
    )

    @Provides
    @Singleton
    fun provideUpdateDescriptionDBRepository(
        dao: UpdateDescriptionDao,
        mapper: Mapper<UpdateDescriptionModel, UpdateDescriptionDBEntity>
    ): UpdateDescriptionDBRepository = UpdateDescriptionDBRepositoryImpl(
        dao, mapper
    )

    @Provides
    @Singleton
    fun provideUpdatesHistoryApi(
        @Qualifiers.UpdatesHistory   retrofit: Retrofit
    ): UpdatesHistoryApi = retrofit.create(UpdatesHistoryApi::class.java)

    @Provides
    @Singleton
    @Qualifiers.UpdatesHistory
    fun provideRetrofitAuth(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
}

class Qualifiers {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UpdatesHistory
}