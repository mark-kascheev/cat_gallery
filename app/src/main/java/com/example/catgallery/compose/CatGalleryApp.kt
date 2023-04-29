package com.example.catgallery.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.catgallery.compose.common.*
import com.example.catgallery.compose.favourites.FavouritesScreen
import com.example.catgallery.compose.gallery.GalleryScreen
import com.example.catgallery.compose.onboarding.OnBoardingScreen
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CatGalleryApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(Color.Black)
        systemUiController.setNavigationBarColor(Color.White)
    }
    Scaffold(
        topBar = {
            if(currentRoute(navController) != onBoardingRoute) AppBar()
        },
        bottomBar = {
            if(currentRoute(navController) != onBoardingRoute) BottomNavigationBar(navController)
    }) {
        CatGalleryAppHost(navController = navController, modifier = Modifier.padding(it), systemUiController = systemUiController)
    }
}

@Composable
fun CatGalleryAppHost(
    modifier: Modifier,
    navController: NavHostController,
    systemUiController: SystemUiController
) {
    NavHost(navController = navController, startDestination = galleryRoute, modifier = modifier) {
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
            systemUiController.setSystemBarsColor(Color.White)
            GalleryScreen()
        }
        composable(favouritesRoute) {
            systemUiController.setSystemBarsColor(Color.White)
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
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primary,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.iconId),
                            modifier = Modifier.size(24.dp),
                            contentDescription = null
                        )
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
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

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}