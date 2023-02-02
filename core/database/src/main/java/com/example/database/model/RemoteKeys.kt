package com.example.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "beer_id") val beerId: Int,
    @ColumnInfo(name = "prev_key") val prevKey: Int?,
    @ColumnInfo(name = "current_page") val currentPage: Int,
    @ColumnInfo(name = "next_key") val nextKey: Int?,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)