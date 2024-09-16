package com.route.news_app.repository.source

import com.route.news_app.data.api.model.sourcesResponse.Source

interface SourcesRepository {
    suspend fun getSourcesByCategoryId(catId: String): List<Source?>?

}

interface SourcesRemoteDataSource {
    suspend fun getSourceByCategoryId(catId: String): List<Source?>?
}
