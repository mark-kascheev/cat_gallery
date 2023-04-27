package com.example.catgallery.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.catgallery.api.CatApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepository @Inject constructor(private val api: CatApi) {
    fun getCatImages(): Flow<PagingData<CatImage>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { CatPagingSource(api) }
        ).flow
    }
}