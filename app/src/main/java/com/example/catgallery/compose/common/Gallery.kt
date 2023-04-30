package com.example.catgallery.compose.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.catgallery.R
import com.example.catgallery.domain.entity.CatImage

@Composable
fun Gallery(modifier: Modifier, data: List<GalleryItemVM>, onFavouriteClick: (GalleryItemVM) -> Unit, onDownloadClick: (String) -> Unit) {
    if(data.isEmpty()) {
        EmptyListState()
    }
    else {
        LazyVerticalGrid(GridCells.Fixed(2), modifier = modifier.fillMaxSize()) {
            items(
                count = data.size,
                key = { index ->
                    val cat = data[index]
                    cat.value.url
                }) {
                    index ->
                val cat = data[index]
                GalleryItem(cat, onFavouriteClick = {onFavouriteClick(cat)}, onDownloadClick = {onDownloadClick(cat.value.url)})
            }
        }
    }
}

@Composable
fun EmptyListState() {
    ListStatePlaceHolder(iconId = R.drawable.ic_empty_list, textId = R.string.empty_list)
}

@Composable
fun ErrorListState() {
    ListStatePlaceHolder(iconId = R.drawable.ic_error, textId = R.string.error_list)
}
@Composable
private fun ListStatePlaceHolder(iconId: Int, textId: Int) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Icon(painter = painterResource(id = iconId), modifier = Modifier.size(150.dp), contentDescription = null)
        Spacer(Modifier.size(15.dp))
        Text(stringResource(id = textId), color = MaterialTheme.colors.secondary)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GalleryItem(vm: GalleryItemVM, onFavouriteClick: () -> Unit, onDownloadClick: () -> Unit) {
    Card(Modifier.padding(16.dp), shape = RoundedCornerShape(CornerSize(10.dp))) {
        Column(Modifier.fillMaxWidth()) {
            GlideImage(
                model = vm.value.url,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                DownloadButton(onDownloadClick = onDownloadClick)
                FavouriteButton(vm.cached, onFavouriteClick = onFavouriteClick)
            }
        }
    }
}

@Composable
private fun FavouriteButton(isCached: Boolean, onFavouriteClick: () -> Unit) {
    IconButton(onClick =  onFavouriteClick) {
        if(isCached) {
            Icon(painter = painterResource(id = R.drawable.ic_filled_fav), modifier = Modifier.size(24.dp), tint = Color.Red, contentDescription = null)
        } else {
            Icon(painter = painterResource(id = R.drawable.ic_outlined_fav), modifier = Modifier.size(24.dp), contentDescription = null)
        }
    }
}

@Composable
private fun DownloadButton(onDownloadClick: () -> Unit) {
    IconButton(onClick =  onDownloadClick) {
        Icon(painter = painterResource(id = R.drawable.ic_download), modifier = Modifier.size(24.dp), contentDescription = null)
    }
}