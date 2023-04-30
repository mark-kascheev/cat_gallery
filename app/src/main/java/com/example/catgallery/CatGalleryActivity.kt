package com.example.catgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.catgallery.compose.CatGalleryApp
import com.example.catgallery.compose.theme.CatAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatGalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatAppTheme {
                CatGalleryApp()
            }
        }
    }
}
