package com.route.news_app.api

import com.route.news_app.newsResponse.NewsResponse
import com.route.news_app.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKey:String,
                   @Query("category")category: String
                   ):Call<SourcesResponse>
    @GET("v2/everything")
    fun getNews(@Query("apiKey") apiKey:String
                ,@Query("sources") sources:String):Call<NewsResponse>
}