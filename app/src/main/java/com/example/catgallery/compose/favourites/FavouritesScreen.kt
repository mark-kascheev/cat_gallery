package com.example.catgallery.compose.favourites

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.catgallery.compose.common.Gallery
import com.example.catgallery.view_model.FavouritesViewModel

@Composable
fun FavouritesScreen(viewModel: FavouritesViewModel = hiltViewModel()) {
    val cats by viewModel.favourites.collectAsState(emptyList())
    Gallery(
        data = cats,
        onFavouriteClick = viewModel::toggleFavourite,
        onDownloadClick = viewModel::downloadImage,
        modifier = Modifier.padding(top = 8.dp)
    )
}