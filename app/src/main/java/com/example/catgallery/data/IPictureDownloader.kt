package com.example.catgallery.data

interface IPictureDownloader {
    suspend fun downloadImage(url: String)
}