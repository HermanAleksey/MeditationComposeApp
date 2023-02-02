package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meditationcomposeapp.data_source.database.dao.UpdateDescriptionDao
import com.example.meditationcomposeapp.data_source.entity.db.UpdateDescriptionDBEntity

@Database(
    entities = [UpdateDescriptionDBEntity::class, BeerListItem::class, RemoteKeys::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun updatesDescriptionDao(): UpdateDescriptionDao

    abstract fun beerDao(): BeerDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}