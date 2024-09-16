package com.route.news_app.data.repositories.news

import com.route.news_app.data.api.model.newsResponse.News
import com.route.news_app.repository.news.NewsRemoteDataSource
import com.route.news_app.repository.news.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    val dataSource: NewsRemoteDataSource
) :
    NewsRepository {
    override suspend fun getNewsBySourceId(sourceId: String): List<News?>? {
        val news = dataSource.getNewsBySourceId(sourceId)
        return news
    }
}