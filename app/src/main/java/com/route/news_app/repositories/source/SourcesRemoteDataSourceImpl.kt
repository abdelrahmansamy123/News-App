package com.route.news_app.repositories.source

import com.route.news_app.api.ApiConstants
import com.route.news_app.api.WebServices
import com.route.news_app.api.model.sourcesResponse.Source
import com.route.news_app.repositoriesContract.source.SourcesRemoteDataSource

class SourcesRemoteDataSourceImpl(val webServices: WebServices) : SourcesRemoteDataSource {
    override suspend fun getSourceByCategoryId(catId: String): List<Source?>? {
        val sourcesResponse = webServices.getSources(ApiConstants.apiKey, catId)
        return sourcesResponse.sources
    }
}