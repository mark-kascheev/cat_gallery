package com.example.catgallery.compose.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            GreetingText()
            StartButton(onStartClick)
        }
    }
}

@Composable
fun StartButton(onStartClick: () -> Unit) {
    Button(
        onClick = onStartClick, modifier = Modifier
            .width(150.dp)
            .height(50.dp)
    ) {
        Text(text = stringResource(id = R.string.start_button), fontSize = 25.sp)
    }
}

@Composable
fun GreetingText() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.big_greeting),
            textAlign = TextAlign.Center,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary
        )
        Text(
            text = stringResource(id = R.string.small_greeting),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun CatImage() {
    Image(
        painter = painterResource(id = R.drawable.onboarding_cat),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )

            ),
        contentScale = ContentScale.Crop
    )
}