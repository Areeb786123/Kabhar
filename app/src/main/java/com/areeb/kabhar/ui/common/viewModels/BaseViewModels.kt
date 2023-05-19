package com.areeb.kabhar.ui.common.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.areeb.kabhar.data.network.Resources

open class BaseViewModels : ViewModel() {

    private var _resource = MutableLiveData<Resources<Any>?>()
    val resources: LiveData<Resources<Any>?> get() = _resource

    fun setResources(resources: Resources<Any>?) {
        _resource.value = resources
    }

    fun clearResources() {
        _resource.value = null
    }

    fun setResourceError(error: String) {
        _resource.value = Resources.ERROR(
            error,
        )
    }
}
