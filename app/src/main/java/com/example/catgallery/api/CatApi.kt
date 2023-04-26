package com.example.catgallery.api

import com.example.catgallery.data.CatImage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("/images/search")
    suspend fun getCatImages(@Query("limit") limit: Int = 10): CatImage

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/v1"

        fun create(): CatApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CatApi::class.java)
        }
    }
}
