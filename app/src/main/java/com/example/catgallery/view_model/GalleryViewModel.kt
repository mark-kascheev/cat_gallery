package com.example.catgallery.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.catgallery.compose.common.GalleryItemVM
import com.example.catgallery.data.CatRepository
import com.example.catgallery.data.FavouritesRepository
import com.example.catgallery.domain.entity.CatImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: CatRepository, private val favouriteRepository: FavouritesRepository): ViewModel() {
    private val favouriteCats = favouriteRepository.getCats()
    private val cats = repository.getCatImages().cachedIn(viewModelScope)

    val combinedCats = combine(cats, favouriteCats) { remote, cached ->
        remote.map { cat ->
            GalleryItemVM(cat, cached.any { it.url == cat.url })
        }
    }

        fun toggleFavourite(item: GalleryItemVM) {
            viewModelScope.launch {
                if(item.cached) {
                    favouriteRepository.deleteCat(item.value)
                }else {
                    favouriteRepository.addCat(item.value)
                }
            }
        }
    }