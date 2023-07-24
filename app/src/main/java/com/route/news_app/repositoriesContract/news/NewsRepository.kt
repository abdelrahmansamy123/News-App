package com.route.news_app.repositoriesContract.news

import com.route.news_app.api.model.newsResponse.News

interface NewsRepository {
    suspend fun getNewsBySourceId(sourceId: String, page: Int, pageSize: Int): List<News?>?
}

interface NewsRemoteDataSource {
    suspend fun getNewsBySourceId(sourceId: String, page: Int, pageSize: Int): List<News?>?
}