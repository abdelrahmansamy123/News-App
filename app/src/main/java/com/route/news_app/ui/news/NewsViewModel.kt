package com.route.news_app.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.route.news_app.api.ApiManager
import com.route.news_app.api.model.newsResponse.News
import com.route.news_app.api.model.newsResponse.NewsResponse
import com.route.news_app.repositories.news.NewsRemoteDataSourceImpl
import com.route.news_app.repositories.news.NewsRepositoryImpl
import com.route.news_app.repositoriesContract.news.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NewsViewModel : ViewModel() {
    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()
    val newsList = MutableLiveData<List<News?>?>()
    val webServices = ApiManager.getApis()
    val newsRemoteDataSource = NewsRemoteDataSourceImpl(webServices)
    val newsRepo: NewsRepository = NewsRepositoryImpl(newsRemoteDataSource)
    fun getNews(sourceId: String, pageSize: Int, page: Int) {
        showLoading.value = true
        viewModelScope.launch {
            try {
                val news = newsRepo.getNewsBySourceId(sourceId, page, pageSize)
                newsList.value = news
                showLoading.value = false
            } catch (t: HttpException) {
                val errorResponse = Gson().fromJson(
                    t.response()?.errorBody()?.string(), NewsResponse::class.java
                )
                showError.value = errorResponse.message ?: ""
            } catch (e: HttpException) {
                showError.value = e.localizedMessage ?: ""
            }

        }

//            .enqueue(object : Callback<NewsResponse> {
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                    showError.value = t.localizedMessage
//                }
//
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        // we have news to show
//                        newsList.value = response.body()?.articles
//                        return
//                    }
//                    val errorResponse = Gson().fromJson(
//                        response.errorBody()?.string(), NewsResponse::class.java
//                    )
//                    showError.value = errprResponse.message ?: ""
//                }
//            })
    }
}