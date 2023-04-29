package com.example.catgallery.compose.favourites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.catgallery.compose.common.GalleryItem
import com.example.catgallery.view_model.FavouritesViewModel

@Composable
fun FavouritesScreen(viewModel: FavouritesViewModel = hiltViewModel()) {
    val cats by viewModel.favourites.collectAsState(emptyList())
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            count = cats.size,
            key = { index ->
                cats[index].value.url
            }) {
                index ->
            val cat = cats[index]
            GalleryItem(cat, onFavouriteClick = {viewModel.toggleFavourite(cat)}, onDownloadClick = {viewModel.downloadImage(cat.value.url)})
        }
    }

}