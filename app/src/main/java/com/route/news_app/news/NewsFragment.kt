package com.route.news_app.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.route.news_app.api.ApiConstants
import com.route.news_app.api.ApiManager
import com.route.news_app.databinding.FragmentNewsBinding
import com.route.news_app.newsResponse.News
import com.route.news_app.newsResponse.NewsResponse
import com.route.news_app.sourcesResponse.Source
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    companion object{
        fun getInstance(source: Source) : NewsFragment{
        val newNewsFragment = NewsFragment()
            newNewsFragment.source = source
            return newNewsFragment
        }
    }
    lateinit var source: Source


    lateinit var viewBinding:FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewsBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getNews()
    }

    val newsAdapter = NewsAdapter(null)
    private fun initRecyclerView() {
        viewBinding.newsRecycler.adapter = newsAdapter
    }

    private fun getNews() {
        ApiManager
            .getApis()
            .getNews(ApiConstants.apiKey, source.id?:"")
            .enqueue(object : Callback<NewsResponse>{
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    showErrorLayout(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful) {
                        // we have news to show
                        bindNewsList(response.body()?.articles)
                        return
                    }
                    val errprResponse = Gson().fromJson(
                        response.errorBody()?.string(), NewsResponse::class.java
                    )
                    showErrorLayout(errprResponse.message)
                  }
            })
    }

    private fun bindNewsList(articles: List<News?>?) {
        // show news in recycler view
        viewBinding.errorLayout.isVisible = false
        viewBinding.loadingIndicator.isVisible = false
        newsAdapter.changeData(articles)
    }

    private fun showErrorLayout(message: String?) {
        viewBinding.errorLayout.isVisible = true
        viewBinding.loadingIndicator.isVisible = false
        viewBinding.errorMessage.text = message
    }

    private fun showLoadingLayout(){
        viewBinding.loadingIndicator.isVisible = true
        viewBinding.errorLayout.isVisible = false
    }
}