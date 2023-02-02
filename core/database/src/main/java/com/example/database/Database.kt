package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.BeerDao
import com.example.database.dao.RemoteKeysDao
import com.example.database.dao.UpdateDescriptionDao
import com.example.database.model.BeerListItem
import com.example.database.model.RemoteKeys
import com.example.database.model.UpdateDescriptionDBEntity

@Database(
    entities = [UpdateDescriptionDBEntity::class, BeerListItem::class, RemoteKeys::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun updatesDescriptionDao(): UpdateDescriptionDao

    abstract fun beerDao(): BeerDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}