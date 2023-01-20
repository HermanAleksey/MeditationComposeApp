package com.example.meditationcomposeapp.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meditationcomposeapp.data_source.database.dao.BeerDao
import com.example.meditationcomposeapp.data_source.database.dao.UpdateDescriptionDao
import com.example.meditationcomposeapp.data_source.entity.db.BeerDB
import com.example.meditationcomposeapp.data_source.entity.db.UpdateDescriptionDBEntity

@Database(
    entities = [UpdateDescriptionDBEntity::class, BeerDB::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun updatesDescriptionDao(): UpdateDescriptionDao

    abstract fun beerDao(): BeerDao
}