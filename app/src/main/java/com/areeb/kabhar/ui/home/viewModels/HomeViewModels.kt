package com.areeb.kabhar.ui.home.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.areeb.kabhar.data.models.Article
import com.areeb.kabhar.data.network.RemoteOperations
import com.areeb.kabhar.ui.home.pagination.PagingSourceFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModels @Inject constructor(private val remoteOperations: RemoteOperations) :
    ViewModel() {

    private val _topHeadlines = MutableLiveData<PagingData<Article>>()
    val topHeadlineResponse: LiveData<PagingData<Article>>
        get() = _topHeadlines

    init {
        getTopHeadlines()
    }

    private fun getNewsPaginationSourceData(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 1, maxSize = 20),
            pagingSourceFactory = { PagingSourceFactory(remoteOperations) },
        ).flow.cachedIn(
            viewModelScope,
        )
    }

    private fun getTopHeadlines() {
        viewModelScope.launch {
            getNewsPaginationSourceData().collectLatest {
                _topHeadlines.value = it
                Log.e("topHeadLinesData", it.toString())
            }
        }
    }
}
