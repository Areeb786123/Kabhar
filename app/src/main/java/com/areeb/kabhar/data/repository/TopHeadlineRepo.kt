package com.areeb.kabhar.data.repository

import com.areeb.kabhar.data.models.TopHeadlineResponse
import com.areeb.kabhar.data.network.RemoteOperations
import com.areeb.kabhar.data.network.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TopHeadlineRepo @Inject constructor(private val remoteOperations: RemoteOperations) {

    fun getNewsHeadlines(country: String): Flow<Resources<TopHeadlineResponse>> {
        return flow {
            val response = remoteOperations.getTopNews()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}
