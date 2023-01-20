package com.example.meditationcomposeapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.meditationcomposeapp.BuildConfig
import com.example.meditationcomposeapp.data_source.database.AppDatabase
import com.example.meditationcomposeapp.data_source.database.dao.BeerDao
import com.example.meditationcomposeapp.data_source.database.dao.UpdateDescriptionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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