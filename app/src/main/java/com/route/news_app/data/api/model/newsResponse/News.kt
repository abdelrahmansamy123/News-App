package com.route.news_app.data.api.model.newsResponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.route.news_app.data.api.model.sourcesResponse.Source
import kotlinx.parcelize.Parcelize


@Parcelize
data class News(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("source")
    val source: Source? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null,

    @field:SerializedName("q")
    val query: String? = null
) : Parcelable