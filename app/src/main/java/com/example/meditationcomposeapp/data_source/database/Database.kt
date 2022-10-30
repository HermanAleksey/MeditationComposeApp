package com.example.meditationcomposeapp.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.meditationcomposeapp.data_source.database.dao.UpdateDescriptionDao
import com.example.meditationcomposeapp.data_source.entity.UpdateDescriptionDBEntity

@Database(
    entities = [UpdateDescriptionDBEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun updatesDescriptionDao(): UpdateDescriptionDao
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
//                    "PRIMARY KEY(`id`))")
        }
    }
}