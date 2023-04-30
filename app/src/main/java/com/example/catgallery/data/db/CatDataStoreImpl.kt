package com.example.catgallery.data.db

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CatDataStoreImpl(private val context: Context) : ICatDataStore {
    private val Context.store: DataStore<Preferences> by preferencesDataStore(name = "app")
    private val firstStartKey = booleanPreferencesKey("first_start")

    override suspend fun isFirstStart(): Boolean {
        return context.store.data.map { pref -> pref[firstStartKey] ?: true }.first()
    }

    override suspend fun updateFirstStart() {
        context.store.edit { pref ->
            pref[firstStartKey] = false
        }
    }
}