package com.route.news_app.data.repositories.source

import com.route.news_app.data.api.model.sourcesResponse.Source
import com.route.news_app.repository.source.SourcesRemoteDataSource
import com.route.news_app.repository.source.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(val remoteDataSource: SourcesRemoteDataSource) :
    SourcesRepository {
    override suspend fun getSourcesByCategoryId(catId: String): List<Source?>? {
        val data = remoteDataSource.getSourceByCategoryId(catId);
        return data
    }
}