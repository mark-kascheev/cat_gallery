package com.example.catgallery.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.catgallery.domain.entity.CatImage

@Database(entities = [CatImage::class], version = 1)
abstract  class CatDatabase : RoomDatabase() {
    abstract fun catDao() : CatDao
}