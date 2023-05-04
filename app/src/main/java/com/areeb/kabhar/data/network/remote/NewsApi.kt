package com.areeb.kabhar.data.network.remote

import com.areeb.kabhar.data.models.TopHeadlineResponse
import com.areeb.kabhar.data.network.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(ApiConstants.EVERYTHING)
    suspend fun getTopNews(
        @Query("q") query: String = "sports",
        @Query("apiKey") apiKey: String = ApiConstants.API_KEY,
    ): TopHeadlineResponse
}
