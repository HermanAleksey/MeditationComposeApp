package com.example.meditationcomposeapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.withTransaction
import com.example.meditationcomposeapp.BuildConfig
import com.example.meditationcomposeapp.data_source.database.AppDatabase
import com.example.meditationcomposeapp.data_source.database.dao.BeerDao
import com.example.meditationcomposeapp.data_source.database.dao.UpdateDescriptionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideUpdateDescriptionDao(
        appDatabase: AppDatabase,
    ): UpdateDescriptionDao = appDatabase.updatesDescriptionDao()

    @Singleton
    @Provides
    fun provideBeerDao(
        appDatabase: AppDatabase,
    ): BeerDao = appDatabase.beerDao()

    @Provides
    fun provideTransactionExecutor(
        appDatabase: AppDatabase,
    ): (scope: CoroutineScope, function: () -> Unit) -> Unit = { scope, function ->
        scope.launch {
            appDatabase.withTransaction {
                function()
            }
        }
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        BuildConfig.DATABASE_NAME
    ).build()
}