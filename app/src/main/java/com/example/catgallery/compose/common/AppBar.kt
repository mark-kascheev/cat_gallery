package com.example.catgallery.compose.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.catgallery.R

@Composable
fun AppBar() {
    Row(
        Modifier
            .padding(top = 8.dp, start = 8.dp, end = 16.dp)
            .fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {}) {
            Icon(painter = painterResource(id=  R.drawable.ic_menu), modifier = Modifier.size(24.dp), contentDescription = null)
        }
        LocationInfo()
        Profile()
    }
}

@Composable
private fun LocationInfo() {
    Column {
        Row(modifier = Modifier.clickable {}, horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
            Text(stringResource(id = R.string.location), color = MaterialTheme.colors.secondary)
            Icon(painter = painterResource(id = R.drawable.ic_arrow), modifier = Modifier
                .size(16.dp)
                .rotate(-90F), contentDescription = null, tint = MaterialTheme.colors.secondary)
        }
        Row {
            Text("${stringResource(id = R.string.location_city_example)}, ")
            Text(stringResource(id = R.string.location_country_example), fontWeight = FontWeight.Bold)
        }
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