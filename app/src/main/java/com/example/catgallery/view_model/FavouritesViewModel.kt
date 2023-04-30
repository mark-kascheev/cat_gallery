package com.example.catgallery.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catgallery.compose.common.GalleryItemVM
import com.example.catgallery.data.FavouritesRepository
import com.example.catgallery.data.IPictureDownloader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val cacheRepository: FavouritesRepository,
    private val downloader: IPictureDownloader
) : ViewModel() {
    private val favouritesState = MutableStateFlow<List<GalleryItemVM>>(emptyList())
    val favourites = favouritesState.asStateFlow()

    init {
        viewModelScope.launch {
            cacheRepository.getCats()
                .map { list -> list.map { cat -> GalleryItemVM(cat, cached = true) } }.collect {
                    favouritesState.value = it
                }
        }
    }

    fun toggleFavourite(item: GalleryItemVM) {
        viewModelScope.launch {
            if (item.cached) {
                cacheRepository.deleteCat(item.value)
            } else {
                cacheRepository.addCat(item.value)
            }
        }
    }

    fun downloadImage(url: String) {
        viewModelScope.launch {
            downloader.downloadImage(url)
        }
    }
}