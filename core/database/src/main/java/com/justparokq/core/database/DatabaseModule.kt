package com.justparokq.core.database

import android.content.Context
import androidx.room.Room
import com.justparokq.core.database.dao.BeerDao
import com.justparokq.core.database.dao.RemoteKeysDao
import com.justparokq.core.database.dao.UpdateDescriptionDao
import com.justparokq.core.database.AppDatabase
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
    fun provideExecuteDatabaseTransactionUseCase(
        appDatabase: AppDatabase,
    ): ExecuteDatabaseTransactionUseCase = ExecuteDatabaseTransactionUseCaseImpl(appDatabase)

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
    fun provideRemoteKeysDao(
        appDatabase: AppDatabase,
    ): RemoteKeysDao = appDatabase.remoteKeysDao()

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