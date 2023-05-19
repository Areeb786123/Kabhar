package com.areeb.kabhar.data.network

sealed class Resources<out T> {
    data class SUCCESS<out T>(val data: T) : Resources<T>()
    data class LOADING<out T>(val value: Boolean) : Resources<T>()
    data class ERROR(val error: String) : Resources<Nothing>()
}
