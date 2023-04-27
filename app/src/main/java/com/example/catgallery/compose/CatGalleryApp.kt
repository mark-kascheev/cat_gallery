package com.example.catgallery.compose

import android.app.Activity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.catgallery.R
import com.example.catgallery.compose.favourites.FavouritesScreen
import com.example.catgallery.compose.gallery.GalleryScreen
import com.example.catgallery.compose.onboarding.OnBoardingScreen

@Composable
fun CatGalleryApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
    }) {
        CatGalleryAppHost(navController)
    }
}

@Composable
fun CatGalleryAppHost(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = galleryRoute) {
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
        composable(favouritesRoute) {
            FavouritesScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Gallery,
        BottomNavItem.Favourites,
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.teal_200),
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.iconId), contentDescription = null) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}