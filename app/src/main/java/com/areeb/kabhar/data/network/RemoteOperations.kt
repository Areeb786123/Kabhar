package com.areeb.kabhar.data.network

import com.areeb.kabhar.data.models.TopHeadlineResponse
import com.areeb.kabhar.data.network.remote.NewsApi
import javax.inject.Inject

class RemoteOperations @Inject constructor(private val newApi: NewsApi) :
    IRemoteOperations,
    SafeApiCall {
    override suspend fun getTopNews(query: String): Resources<TopHeadlineResponse> {
        return safeApiCall { newApi.getTopNews(query) }
    }
}
