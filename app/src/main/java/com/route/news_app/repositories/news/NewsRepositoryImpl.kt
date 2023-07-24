package com.route.news_app.repositories.news

import com.route.news_app.api.model.newsResponse.News
import com.route.news_app.repositoriesContract.news.NewsRemoteDataSource
import com.route.news_app.repositoriesContract.news.NewsRepository

class NewsRepositoryImpl(val dataSource: NewsRemoteDataSource) : NewsRepository {
    override suspend fun getNewsBySourceId(
        sourceId: String,
        page: Int,
        pageSize: Int
    ): List<News?>? {
        val news = dataSource.getNewsBySourceId(sourceId, page, pageSize)
        return news
    }
}