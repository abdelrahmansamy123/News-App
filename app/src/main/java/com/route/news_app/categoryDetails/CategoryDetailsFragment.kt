package com.route.news_app.categoryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.route.news_app.R
import com.route.news_app.api.ApiConstants
import com.route.news_app.api.ApiManager
import com.route.news_app.categories.Category
import com.route.news_app.databinding.FragmentDetailsCategoryBinding
import com.route.news_app.news.NewsFragment
import com.route.news_app.sourcesResponse.Source
import com.route.news_app.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsFragment : Fragment() {
    lateinit var viewBinding: FragmentDetailsCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDetailsCategoryBinding.inflate(
            inflater, container, false)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNewsSources()


    }
    fun changeNewsFragment(source: Source) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsFragment.getInstance(source))
            .commit()
    }
    private fun loadNewsSources() {
        showLoadingLayout()
        ApiManager
            .getApis()
            .getSources(ApiConstants.apiKey, category.id)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>, response: Response<SourcesResponse>
                ) {
//                    viewBinding.loadingIndicator.visibility = View.GONE
                    viewBinding.loadingIndicator.isVisible = false
                    if (response.isSuccessful) {
                        bindSourcesInTabLayout(response.body()?.sources)
                    } else {
                        val gson = Gson()
                        val errorResponse = gson.fromJson(
                            response.errorBody()?.string(), SourcesResponse::class.java
                        )
                        showErrorLayout(errorResponse.message)
                        errorResponse
                    }

                    //                    response.body().sources
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    viewBinding.loadingIndicator.isVisible = false
                    showErrorLayout(t.localizedMessage)
                }
            })
    }
    private fun showErrorLayout(message: String?) {
        viewBinding.errorLayout.isVisible = true
        viewBinding.loadingIndicator.isVisible = false
        viewBinding.errorMessage.text = message
    }
    private fun showLoadingLayout() {
        viewBinding.loadingIndicator.isVisible = true
        viewBinding.errorLayout.isVisible = false
    }
    fun bindSourcesInTabLayout(sourcesList: List<Source?>?) {
        sourcesList?.forEach { sources ->
            val tab = viewBinding.tabLayout.newTab()

            tab.text = sources?.name
            tab.tag = sources
            viewBinding.tabLayout.addTab(tab)
            val layoutParams = LinearLayout.LayoutParams(tab.view.layoutParams)
            layoutParams.marginEnd = 12
            layoutParams.marginStart = 12
            tab.view.layoutParams = layoutParams
        }
        viewBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    changeNewsFragment(source)
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    changeNewsFragment(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

            })
        viewBinding.tabLayout.getTabAt(0)?.select()

    }

    lateinit var category: Category
    companion object{
        fun getInstance(category: Category):CategoryDetailsFragment{
            val fragment = CategoryDetailsFragment()
            fragment.category = category
            return fragment
        }
    }
}