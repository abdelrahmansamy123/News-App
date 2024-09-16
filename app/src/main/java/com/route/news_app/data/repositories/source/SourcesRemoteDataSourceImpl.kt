package com.route.news_app.data.repositories.source

import com.route.news_app.data.api.ApiConstants
import com.route.news_app.data.api.WebServices
import com.route.news_app.data.api.model.sourcesResponse.Source
import com.route.news_app.repository.source.SourcesRemoteDataSource
import javax.inject.Inject

class SourcesRemoteDataSourceImpl @Inject constructor(val webServices: WebServices) :
    SourcesRemoteDataSource {
    override suspend fun getSourceByCategoryId(catId: String): List<Source?>? {
        val sourcesResponse = webServices.getSources(
            ApiConstants.apiKey,
            catId
        )
        return sourcesResponse.sources
    }
}