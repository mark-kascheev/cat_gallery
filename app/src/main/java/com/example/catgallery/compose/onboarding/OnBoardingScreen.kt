package com.example.catgallery.compose.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.catgallery.R

@Composable
fun OnBoardingScreen(
    onStartClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CatImage()
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround,) {
            GreetingText()
            StartButton(onStartClick)
        }
    }
}

@Composable
fun StartButton(onStartClick: () -> Unit) {
    Button(onClick = onStartClick) {
        Text(text = "Start")
    }
}

@Composable
fun GreetingText() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Text(text = "Everything for cat lovers", textAlign = TextAlign.Center)
        Text(text = "We helps you find the all type of cat you want for free", textAlign = TextAlign.Center)
    }
}

@Composable
fun CatImage() {
    Image(painter = painterResource(id = R.drawable.onboarding_cat), contentDescription = "", modifier = Modifier
        .fillMaxWidth()
        .clip(
            shape = RoundedCornerShape(
                bottomStart = 100.dp,
                bottomEnd = 100.dp
            )

        ), contentScale = ContentScale.Crop)
}