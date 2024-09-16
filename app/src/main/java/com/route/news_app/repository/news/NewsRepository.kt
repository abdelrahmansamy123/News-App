package com.route.news_app.repository.news

import com.route.news_app.data.api.model.newsResponse.News

interface NewsRepository {
    suspend fun getNewsBySourceId(sourceId: String): List<News?>?
}

interface NewsRemoteDataSource {
    suspend fun getNewsBySourceId(sourceId: String): List<News?>?
}