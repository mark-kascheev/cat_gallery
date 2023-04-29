package com.example.catgallery.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun CatAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content, colors = LightColors)
}

private val LightColors = lightColors(
    primary = Colors.DarkGrey,
    secondary = Colors.Grey,
)