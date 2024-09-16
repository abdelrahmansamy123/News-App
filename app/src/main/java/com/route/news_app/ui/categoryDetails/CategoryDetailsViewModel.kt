package com.route.news_app.ui.categoryDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.route.news_app.data.api.model.sourcesResponse.Source
import com.route.news_app.data.api.model.sourcesResponse.SourcesResponse
import com.route.news_app.repository.source.SourcesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(val sourcesRepository: SourcesRepository) :
    ViewModel() {

    val sourcesLivedata = MutableLiveData<List<Source?>?>()
    val showLoadingLayout = MutableLiveData<Boolean>()
    val showErrorLayout = MutableLiveData<String>()


    fun loadNewsSources(categoryId: String) {
        viewModelScope.launch {
            showLoadingLayout.value = true
            try {
                val sources = sourcesRepository.getSourcesByCategoryId(categoryId)
                showLoadingLayout.value = false
                sourcesLivedata.value = sources

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
    }
}