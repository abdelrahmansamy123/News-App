package com.route.news_app.data.repositories.news

import com.route.news_app.repository.news.NewsRemoteDataSource
import com.route.news_app.repository.news.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsRepositoryModule {
    @Binds
    abstract fun getNewsRepo(newsRepository: NewsRepositoryImpl)
            : NewsRepository
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsDataSourceModule {
    @Binds
    abstract fun bindNewsRemoteDataSource(
        newsDataSource: NewsRemoteDataSourceImpl
    ): NewsRemoteDataSource
}