package com.areeb.kabhar.data.models

data class TopHeadlineResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
) 
