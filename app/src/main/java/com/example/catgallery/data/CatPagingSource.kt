package com.example.catgallery.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catgallery.api.CatApi
import com.example.catgallery.domain.entity.CatImage

class CatPagingSource (
    private val api: CatApi,
    ) : PagingSource<Int, CatImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatImage> {
        return try {
            val page = params.key ?: 1
            val response = api.getCatImages().map {dto -> dto.toModel()}
            return  LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = page.plus(1),
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}