package com.areeb.kabhar.data.network

sealed class Resources<out T> {
    data class SUCCESS<out T>(val data: T) : Resources<T>()
    data class LOADING(val value: Boolean) : Resources<Nothing>()
    data class ERROR(val error: String) : Resources<Nothing>()
}
