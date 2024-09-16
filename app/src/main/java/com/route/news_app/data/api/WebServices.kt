package com.route.news_app.data.api

import com.route.news_app.data.api.model.newsResponse.NewsResponse
import com.route.news_app.data.api.model.sourcesResponse.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): SourcesResponse

    @GET("v2/everything")
    suspend fun getNews(
        @Query("apiKey") key: String = ApiConstants.apiKey,
        @Query("sources") sources: String? = null
    ): NewsResponse
}