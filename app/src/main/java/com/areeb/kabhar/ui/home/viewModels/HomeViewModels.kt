package com.areeb.kabhar.ui.home.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.areeb.kabhar.data.models.TopHeadlineResponse
import com.areeb.kabhar.data.network.Resources
import com.areeb.kabhar.data.repository.TopHeadlineRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModels @Inject constructor(private val repository: TopHeadlineRepo) : ViewModel() {

    fun getTopHeadlines(country: String) {
        viewModelScope.launch {
            repository.getNewsHeadlines(country).collect {
                isResource(it)
            }
        }
    }

    private fun isResource(resources: Resources<TopHeadlineResponse>) {
        if (resources is Resources.SUCCESS) {
            Log.e("topXX", resources.data.toString())
        }
    }
}
