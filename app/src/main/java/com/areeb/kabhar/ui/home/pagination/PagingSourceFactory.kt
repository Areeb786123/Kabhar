package com.areeb.kabhar.ui.home.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.areeb.kabhar.data.models.Article
import com.areeb.kabhar.data.network.RemoteOperations
import com.areeb.kabhar.data.network.Resources

class PagingSourceFactory(private val remoteOperations: RemoteOperations) :
    PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val position = params.key ?: 1
            val prevKey = if (position == 1) null else -1

            val resourceData = remoteOperations.getTopNews()

            if (resourceData is Resources.SUCCESS) {
                Log.e("dataXX", resourceData.data.articles.toString())
                return LoadResult.Page(
                    resourceData.data.articles,
                    prevKey,
                    if (resourceData.data.articles.isEmpty()) {
                        null
                    } else {
                        position + 1
                    },
                )
            } else {
                return LoadResult.Error(throw IllegalArgumentException("Invalid input"))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
