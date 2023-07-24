package com.route.news_app.repositoriesContract.source

import com.route.news_app.api.model.sourcesResponse.Source

interface SourcesRepository {
    suspend fun getSourcesByCategoryId(catId: String): List<Source?>?
}

interface SourcesRemoteDataSource {
    suspend fun getSourceByCategoryId(catId: String): List<Source?>?
}

interface SourcesLocalDataSource {

}