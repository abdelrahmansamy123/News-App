package com.route.news_app.data.repositories.news

import com.route.news_app.data.api.ApiConstants
import com.route.news_app.data.api.WebServices
import com.route.news_app.data.api.model.newsResponse.News
import com.route.news_app.repository.news.NewsRemoteDataSource
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(val webServices: WebServices) :
    NewsRemoteDataSource {

    override suspend fun getNewsBySourceId(sourceId: String): List<News?>? {
        val response = webServices.getNews(ApiConstants.apiKey, sourceId)
        return response.articles
    }
}