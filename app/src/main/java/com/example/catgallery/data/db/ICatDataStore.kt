package com.example.catgallery.data.db

interface ICatDataStore {
    suspend fun isFirstStart(): Boolean

    suspend fun updateFirstStart()
}