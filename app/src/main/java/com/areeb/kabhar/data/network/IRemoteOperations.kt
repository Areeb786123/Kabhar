package com.areeb.kabhar.data.network

import com.areeb.kabhar.data.models.TopHeadlineResponse

interface IRemoteOperations {
    suspend fun getTopNews(query: String): Resources<TopHeadlineResponse>
}
