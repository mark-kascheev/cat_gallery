package com.example.catgallery.di

import android.content.Context
import androidx.room.Room
import com.example.catgallery.data.IPictureDownloader
import com.example.catgallery.data.PictureDownloaderImpl
import com.example.catgallery.data.db.CatDao
import com.example.catgallery.data.db.CatDataStoreImpl
import com.example.catgallery.data.db.CatDatabase
import com.example.catgallery.data.db.ICatDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): CatDatabase {
        return Room.databaseBuilder(
            context,
            CatDatabase::class.java, "cats"
        ).build()
    }

    @Provides
    fun provideCatDao(catDatabase: CatDatabase): CatDao {
        return catDatabase.catDao()
    }

    @Singleton
    @Provides
    fun providePictureDownloader(@ApplicationContext context: Context): IPictureDownloader {
        return PictureDownloaderImpl(context)
    }

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): ICatDataStore {
        return CatDataStoreImpl(context)
    }
}