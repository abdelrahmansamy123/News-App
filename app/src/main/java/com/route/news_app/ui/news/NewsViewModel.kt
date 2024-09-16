package com.route.news_app.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.route.news_app.data.api.model.newsResponse.News
import com.route.news_app.data.api.model.newsResponse.NewsResponse
import com.route.news_app.repository.news.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(val newsRepo: NewsRepository) : ViewModel() {
    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()
    val newsList = MutableLiveData<List<News?>?>()
    fun getNews(sourceId: String) {
        showLoading.value = true
        viewModelScope.launch {
            try {
                val news = newsRepo.getNewsBySourceId(sourceId)
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
    }
}