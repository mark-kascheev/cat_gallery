package com.example.catgallery.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catgallery.domain.entity.CatImage

@Database(entities = [CatImage::class], version = 1)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}