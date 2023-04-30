package com.example.catgallery.compose

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catgallery.compose.gallery.GalleryScreen
import com.example.catgallery.compose.onboarding.OnBoardingScreen

const val onBoardingRoute = "onBoarding"
const val galleryRoute = "gallery"

@Composable
fun CatGalleryApp() {
    val navController = rememberNavController()
    CatGalleryAppHost(
        navController = navController,
    )
}

@Composable
fun CatGalleryAppHost(
    navController: NavHostController,
) {
    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = onBoardingRoute) {
        composable(onBoardingRoute) {
            OnBoardingScreen(onStartClick = {
                navController.navigate(galleryRoute) {
                    popUpTo(onBoardingRoute) {
                        inclusive = true
                    }
                }
            })
        }
        composable(galleryRoute) {
            GalleryScreen()
        }
    }
}