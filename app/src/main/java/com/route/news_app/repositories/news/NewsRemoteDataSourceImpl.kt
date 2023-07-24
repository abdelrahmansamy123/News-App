package com.route.news_app.repositories.news

import com.route.news_app.api.ApiConstants
import com.route.news_app.api.WebServices
import com.route.news_app.api.model.newsResponse.News
import com.route.news_app.repositoriesContract.news.NewsRemoteDataSource

class NewsRemoteDataSourceImpl(val webServices: WebServices) : NewsRemoteDataSource {
    override suspend fun getNewsBySourceId(
        sourceId: String,
        page: Int,
        pageSize: Int
    ): List<News?>? {
        val response = webServices.getNews(ApiConstants.apiKey, sourceId, pageSize, page)
        return response.articles
    }
}