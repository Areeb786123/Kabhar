package com.areeb.kabhar.ui.home.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.areeb.kabhar.data.models.Article
import com.areeb.kabhar.data.network.RemoteOperations
import com.areeb.kabhar.data.network.Resources
import com.areeb.kabhar.ui.common.viewModels.BaseViewModels
import com.areeb.kabhar.ui.home.pagination.PagingSourceFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModels @Inject constructor(private val remoteOperations: RemoteOperations) :
    BaseViewModels() {

    private val _topHeadlines = MutableLiveData<PagingData<Article>>()
    val topHeadlineResponse: LiveData<PagingData<Article>>
        get() = _topHeadlines

    private var _chipSelectedValue = MutableLiveData<Int>()
    val chipSelectedValue: LiveData<Int> get() = _chipSelectedValue

    init {
        getTopHeadlines("trending")
    }

    fun setChipSelectedValue(value: Int = 0) {
        _chipSelectedValue.value = value
    }

    fun getChipValue(): Int {
        return _chipSelectedValue.value ?: 0
    }

    private fun getNewsPaginationSourceData(query: String): Flow<PagingData<Article>> {
        setResources(Resources.LOADING(true))
        return Pager(
            config = PagingConfig(pageSize = 1, maxSize = 20),
            pagingSourceFactory = { PagingSourceFactory(remoteOperations, query) },
        ).flow.cachedIn(
            viewModelScope,

        )
    }

    fun getTopHeadlines(query: String) {
        viewModelScope.launch {
            getNewsPaginationSourceData(query).collectLatest {
                setResources(Resources.SUCCESS(true))
                _topHeadlines.value = it
                Log.e("topHeadLinesData", it.toString())
            }
        }
    }
}
