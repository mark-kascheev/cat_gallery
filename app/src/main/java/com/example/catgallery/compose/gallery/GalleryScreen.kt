package com.example.catgallery.compose.gallery

import android.graphics.BlurMaskFilter
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catgallery.R
import com.example.catgallery.view_model.GalleryViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.catgallery.compose.common.*
import kotlinx.coroutines.flow.Flow

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel = hiltViewModel(),
) {
    Column {
        SearchBar()
        Gallery(viewModel.combinedCats, onFavouriteClick = viewModel::toggleFavourite, onDownloadClick= viewModel::downloadImage)
    }
}

@Composable
private fun SearchBar() {
    Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp), verticalAlignment = Alignment.CenterVertically) {
            Card(
                Modifier
                    .height(45.dp)
                    .weight(7f), elevation = 3.dp) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp), contentAlignment = Alignment.Center) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(id = R.drawable.ic_search), modifier = Modifier.size(16.dp), contentDescription = null)
                        Box(Modifier.size(10.dp))
                        BasicTextField(
                            value = "",
                            onValueChange = {},
                            singleLine = true,
                            decorationBox = { innerTextField ->
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = stringResource(id = R.string.search_hint),
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        )
                    }
                }
            }
        Spacer(Modifier.weight(1f))
        Button(onClick = {}, modifier = Modifier.size(45.dp), contentPadding = PaddingValues(
            all= 0.dp
        )) {
            Icon(painter = painterResource(id = R.drawable.ic_filter), modifier = Modifier.size(24.dp), contentDescription = null)
        }
    }
}

@Composable
private fun Gallery(cats: Flow<PagingData<GalleryItemVM>>, onFavouriteClick: (GalleryItemVM) -> Unit,onDownloadClick: (String) -> Unit) {
    val pagingItems: LazyPagingItems<GalleryItemVM> = cats.collectAsLazyPagingItems()
    when(pagingItems.loadState.refresh) {
        is LoadState.NotLoading -> Unit
        is LoadState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is LoadState.Error -> {
            ErrorListState()
        }
    }
    Column {
        LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
            items(
                count = pagingItems.itemCount,
                key = { index ->
                    val cat = pagingItems[index]
                    cat?.value?.url ?: ""
                }) {
                    index ->
                val cat = pagingItems[index] ?: return@items
                GalleryItem(cat, onFavouriteClick = {onFavouriteClick(cat)}, onDownloadClick = {onDownloadClick(cat.value.url)})
            }
        }
        when (pagingItems.loadState.append) { // Pagination
            is LoadState.Error -> {
                Toast.makeText(LocalContext.current,  stringResource(id = R.string.error_pagination), Toast.LENGTH_SHORT).show()
            }
            is LoadState.Loading -> { // Pagination Loading UI
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            else -> {}
        }
    }
}