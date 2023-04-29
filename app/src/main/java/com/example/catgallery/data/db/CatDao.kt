package com.example.catgallery.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.catgallery.domain.entity.CatImage
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM cats")
    fun getCats(): Flow<List<CatImage>>

    @Insert
    suspend  fun addCat(cat: CatImage)

    @Delete
    suspend fun deleteCat(cat: CatImage)


}