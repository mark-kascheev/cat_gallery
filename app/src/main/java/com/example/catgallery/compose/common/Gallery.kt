package com.example.catgallery.compose.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.catgallery.R
import com.example.catgallery.domain.entity.CatImage


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GalleryItem(vm: GalleryItemVM, onFavouriteClick: () -> Unit) {
    Card {
        Box {
            GlideImage(
                model = vm.value.url,
                modifier = Modifier.size(100.dp),
                contentDescription = null,
            )
            FavouriteButton(vm.cached, onFavouriteClick = onFavouriteClick)
        }
    }
}

@Composable
private fun FavouriteButton(isCached: Boolean, onFavouriteClick: () -> Unit) {
    IconButton(onClick =  onFavouriteClick) {
        Icon(painter = painterResource(id = R.drawable.ic_filled_fav), modifier = Modifier.size(24.dp), tint = if(isCached) Color.Red else Color.Gray, contentDescription = null)
    }
}