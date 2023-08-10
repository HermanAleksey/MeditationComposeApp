package com.justparokq.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.justparokq.core.database.dao.BeerDao
import com.justparokq.core.database.dao.RemoteKeysDao
import com.justparokq.core.database.dao.UpdateDescriptionDao
import com.justparokq.core.database.model.BeerListItem
import com.justparokq.core.database.model.RemoteKeys
import com.justparokq.core.database.model.UpdateDescriptionDBEntity

@Database(
    entities = [UpdateDescriptionDBEntity::class, BeerListItem::class, RemoteKeys::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun updatesDescriptionDao(): UpdateDescriptionDao

    abstract fun beerDao(): BeerDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}