package com.areeb.kabhar.data.network.remote

import com.areeb.kabhar.data.network.ApiConstants
import com.areeb.kabhar.data.models.TopHeadlineResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(ApiConstants.TOP_HEADLINES)
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("apikey") apikey: String = ApiConstants.API_KEY,
    ): TopHeadlineResponse
}
