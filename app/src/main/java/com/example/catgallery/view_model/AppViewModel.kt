package com.example.catgallery.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catgallery.data.db.ICatDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val dataStore: ICatDataStore) : ViewModel() {

    suspend fun checkFirstStart(): Boolean {
        return dataStore.isFirstStart()
    }

    fun onNextClick() {
        viewModelScope.launch {
            dataStore.updateFirstStart()
        }
    }
}