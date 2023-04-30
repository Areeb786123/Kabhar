package com.areeb.kabhar.data.network

interface SafeApiCall {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resources<T> {
        return try {
            Resources.SUCCESS(apiCall.invoke())
        } catch (e: Exception) {
            Resources.ERROR(e.toString())
        }
    }
}
