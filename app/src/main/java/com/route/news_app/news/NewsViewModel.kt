package com.route.news_app.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.route.news_app.api.ApiConstants
import com.route.news_app.api.ApiManager
import com.route.news_app.newsResponse.News
import com.route.news_app.newsResponse.NewsResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NewsViewModel : ViewModel() {
    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()
    val newsList = MutableLiveData<List<News?>?>()
    fun getNews(sourceId: String, pageSize: Int, page: Int) {
        showLoading.value = true
        viewModelScope.launch {
            try {
                val serverData = ApiManager
                    .getApis()
                    .getNews(
                        ApiConstants.apiKey,
                        sources = sourceId,
                        pageSize = pageSize,
                        page = page
                    )
                newsList.value = serverData.articles
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