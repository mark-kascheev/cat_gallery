package com.example.catgallery.data

import com.example.catgallery.domain.entity.CatImage
import com.google.gson.annotations.SerializedName

data class CatImageDto(
    @field:SerializedName("url") val url: String,
    @field:SerializedName("width") val width: Int,
    @field:SerializedName("height") val height: Int
)

fun CatImageDto.toModel(): CatImage = CatImage(url = url, width = width, height = height)