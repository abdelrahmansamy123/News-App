package com.route.news_app.repositories.source

import com.route.news_app.api.model.sourcesResponse.Source
import com.route.news_app.repositoriesContract.source.SourcesRemoteDataSource
import com.route.news_app.repositoriesContract.source.SourcesRepository

class SourcesRepositoryImpl(val remoteDataSource: SourcesRemoteDataSource) : SourcesRepository {
    override suspend fun getSourcesByCategoryId(catId: String): List<Source?>? {
        val data = remoteDataSource.getSourceByCategoryId(catId)
        return data
    }
}