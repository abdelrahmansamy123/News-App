package com.route.news_app.categoryDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.route.news_app.api.ApiConstants
import com.route.news_app.api.ApiManager
import com.route.news_app.sourcesResponse.Source
import com.route.news_app.sourcesResponse.SourcesResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CategoryDetailsViewModel : ViewModel() {

    val sourcesLivedata = MutableLiveData<List<Source?>?>()
    val showLoadingLayout = MutableLiveData<Boolean>()
    val showErrorLayout = MutableLiveData<String>()
    fun loadNewsSources(categoryId: String) {
        viewModelScope.launch {
            showLoadingLayout.value = true
            try {
                val serverData = ApiManager
                    .getApis()
                    .getSources(ApiConstants.apiKey, categoryId)
                showLoadingLayout.value = false
                sourcesLivedata.value = serverData.sources

            } catch (t: HttpException) {
                val errorResponse: SourcesResponse =
                    Gson().fromJson(
                        t.response()?.errorBody()?.string(),
                        SourcesResponse::class.java
                    )
                showLoadingLayout.value = false
                showErrorLayout.value = errorResponse.message ?: ""
            } catch (e: Exception) {
                showErrorLayout.value = e.localizedMessage ?: ""
            }

        }

//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onResponse(
//                    call: Call<SourcesResponse>, response: Response<SourcesResponse>
//                ) {
//                    showLoadingLayout.value = false
//                    //   viewBinding.loadingIndicator.isVisible = false
//                    if (response.isSuccessful) {
//                        // we have data
//                        sourcesLivedata.value = response.body()?.sources
//                    } else {
//                        val gson = Gson()
//                        val errorResponse = gson.fromJson(
//                            response.errorBody()?.string(), SourcesResponse::class.java
//                        )
//
//                        showErrorLayout.value = errorResponse.message ?: ""
//                    }
//
//                    //                    response.body().sources
//                }
//
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//                    showLoadingLayout.value = false
//                    showErrorLayout.value = t.localizedMessage ?: ""
//
//                }
//            })
    }
}