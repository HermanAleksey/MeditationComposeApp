package com.example.meditationcomposeapp.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meditationcomposeapp.BuildConfig
import com.example.meditationcomposeapp.data_source.database.dao.UpdateDescriptionDao
import com.example.meditationcomposeapp.data_source.entity.UpdateDescriptionDBEntity

@Database(entities = [UpdateDescriptionDBEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun updatesDescriptionDao(): UpdateDescriptionDao
}