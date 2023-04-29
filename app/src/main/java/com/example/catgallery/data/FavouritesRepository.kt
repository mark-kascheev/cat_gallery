package com.example.catgallery.data

import com.example.catgallery.data.db.CatDao
import com.example.catgallery.domain.entity.CatImage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouritesRepository @Inject constructor(private val catDao: CatDao) {

    fun getCats() = catDao.getCats()

    suspend fun deleteCat(catImage: CatImage) = catDao.deleteCat(catImage)

    suspend fun addCat(catImage: CatImage) = catDao.addCat(catImage)
}