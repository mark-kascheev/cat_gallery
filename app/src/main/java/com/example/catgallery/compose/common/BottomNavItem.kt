package com.example.catgallery.compose.common

import com.example.catgallery.R

const val onBoardingRoute = "onBoarding"
const val galleryRoute = "gallery"
const val favouritesRoute = "favourites"

enum class BottomNavItem(val iconId: Int, val route: String) {
    Gallery(iconId = R.drawable.ic_gallery, route = galleryRoute),
    Favourites(iconId = R.drawable.ic_filled_fav, route = favouritesRoute)
}
