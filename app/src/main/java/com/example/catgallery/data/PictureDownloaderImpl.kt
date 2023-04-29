package com.example.catgallery.data

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PictureDownloaderImpl(private val context: Context) : IPictureDownloader {
    override suspend fun downloadImage(url: String) {
        withContext(Dispatchers.IO) {
            val downloadManager =
                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val imageUri = Uri.parse(url)
            val request = DownloadManager.Request(imageUri)
                .setTitle("Image Download")
                .setDescription("Downloading")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")
            downloadManager.enqueue(request)
        }
    }
}