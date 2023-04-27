package com.example.catgallery.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.catgallery.data.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: CatRepository): ViewModel() {
        val cats =
            repository.getCatImages().cachedIn(viewModelScope)
    }