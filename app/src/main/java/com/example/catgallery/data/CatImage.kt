package com.example.catgallery.data

import com.google.gson.annotations.SerializedName

data class CatImage(
    @field:SerializedName("url") val url: String,
    @field:SerializedName("width") val width: Int,
    @field:SerializedName("height") val height: Int,
    )
