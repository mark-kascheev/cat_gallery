package com.example.catgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import com.example.catgallery.compose.CatGalleryApp
import com.example.catgallery.compose.theme.CatAppTheme
import com.example.catgallery.view_model.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatGalleryActivity : ComponentActivity() {
    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isFirst by remember { mutableStateOf<Boolean?>(null) }

            LaunchedEffect(true) {
                isFirst = viewModel.checkFirstStart()
            }

            if (isFirst != null) {
                CatAppTheme {
                    CatGalleryApp(
                        isFirstStart = isFirst ?: true,
                        onNextClick = { viewModel.onNextClick() }
                    )
                }
            }
        }
    }
}
