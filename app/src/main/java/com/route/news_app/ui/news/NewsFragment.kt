package com.route.news_app.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.route.news_app.data.api.model.newsResponse.News
import com.route.news_app.data.api.model.sourcesResponse.Source
import com.route.news_app.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    companion object {
        fun getInstance(source: Source): NewsFragment {
            val newNewsFragment = NewsFragment()
            newNewsFragment.source = source
            return newNewsFragment
        }
    }

    lateinit var source: Source

    lateinit var viewBinding: FragmentNewsBinding
    val viewModel: NewsViewModel by viewModels()
    var loading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.getNews(source.id ?: "");
        subscribeToLivedata()

    }

    @Inject
    lateinit var newsAdapter: NewsAdapter

    private fun initRecyclerView() {
        viewBinding.newsRecycler.adapter = newsAdapter

    }


    fun subscribeToLivedata() {
        viewModel.newsList.observe(viewLifecycleOwner) {
            bindNewsList(it)
        }
        viewModel.showError.observe(viewLifecycleOwner) {
            showErrorLayout(it)
        }
        viewModel.showLoading.observe(viewLifecycleOwner) {
            if (it)
                showLoadingLayout()
            else
                hideLoadingLayout()
        }
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

    private fun showLoadingLayout() {
        viewBinding.loadingIndicator.isVisible = true
        viewBinding.errorLayout.isVisible = false
    }

    private fun hideLoadingLayout() {
        viewBinding.loadingIndicator.isVisible = false

    }
}