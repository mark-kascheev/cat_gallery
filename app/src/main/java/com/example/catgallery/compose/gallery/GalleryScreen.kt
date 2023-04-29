package com.example.catgallery.compose.gallery

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.catgallery.R
import com.example.catgallery.view_model.GalleryViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.catgallery.compose.common.GalleryItem
import com.example.catgallery.compose.common.GalleryItemVM
import kotlinx.coroutines.flow.Flow

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel = hiltViewModel(),
) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        AppBar()
        SearchBar()
        AnimalList()
        Gallery(viewModel.combinedCats, onFavouriteClick = viewModel::toggleFavourite, onDownloadClick= viewModel::downloadImage)
    }
}

@Composable
private fun AppBar() {
    Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id=  R.drawable.ic_menu), modifier = Modifier.size(24.dp), contentDescription = null)
        }
        LocationInfo()
        Profile()
    }
}

@Composable
private fun LocationInfo() {
    Column {
        Row(modifier = Modifier.clickable {  }, horizontalArrangement = Arrangement.spacedBy(15.dp), verticalAlignment = Alignment.Top) {
            Text("Location")
            Icon(painter = painterResource(id = R.drawable.ic_arrow), modifier = Modifier
                .size(16.dp)
                .rotate(-90F), contentDescription = null)
        }
        Text("Vancouver, Canada")
    }
}

@Composable
private fun Profile() {
    Image(painter = painterResource(id = R.drawable.profile_picture), modifier = Modifier
        .size(50.dp)
        .clip(
            CircleShape
        ), contentScale = ContentScale.Crop, contentDescription = null)
}

@Composable
private fun SearchBar() {
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Card(Modifier.weight(2f), elevation = 3.dp) {
                TextField(
                    value = "",
                    onValueChange = {},
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            modifier = Modifier.size(16.dp),
                            contentDescription = null
                        )
                    },
                    placeholder = { Text(text = "Search") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                )
            }
        Button(onClick = {}, modifier = Modifier.size(45.dp), contentPadding = PaddingValues(
            all= 0.dp
        )) {
            Icon(painter = painterResource(id = R.drawable.ic_filter), modifier = Modifier.size(24.dp), contentDescription = null)
        }
    }
}

@Composable
private fun AnimalList() {
    Row(modifier = Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        AnimalItem("All")
        AnimalItem("Cat")
        AnimalItem("Dogs")
        AnimalItem("Birds")
        AnimalItem("Parrots")
    }

}

@Composable
private fun AnimalItem(title: String) {
    Box(
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .width(60.dp)
            .background(color = Color.Gray)
            .padding(all = 5.dp)
            .clickable { }, contentAlignment = Alignment.Center) {
        Text(title)
    }
}

@Composable
private fun Gallery(cats: Flow<PagingData<GalleryItemVM>>, onFavouriteClick: (GalleryItemVM) -> Unit,onDownloadClick: (String) -> Unit) {
    val pagingItems: LazyPagingItems<GalleryItemVM> = cats.collectAsLazyPagingItems()
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
}

fun Modifier.shadow(
    color: Color = Color.Black,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
) = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            frameworkPaint.color = color.toArgb()

            val leftPixel = offsetX.toPx()
            val topPixel = offsetY.toPx()
            val rightPixel = size.width + topPixel
            val bottomPixel = size.height + leftPixel

            canvas.drawRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                paint = paint,
            )
        }
    })