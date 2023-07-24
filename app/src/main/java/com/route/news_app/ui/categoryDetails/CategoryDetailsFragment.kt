package com.route.news_app.ui.categoryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.route.news_app.R
import com.route.news_app.api.model.sourcesResponse.Source
import com.route.news_app.databinding.FragmentDetailsCategoryBinding
import com.route.news_app.ui.categories.Category
import com.route.news_app.ui.news.NewsFragment

class CategoryDetailsFragment : Fragment() {
    lateinit var viewBinding: FragmentDetailsCategoryBinding
    lateinit var viewModel: CategoryDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDetailsCategoryBinding.inflate(
            inflater, container, false
        )
        return viewBinding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(this).get(CategoryDetailsViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadNewsSources(category.id)
        subscribeToLivedata()
    }

    fun subscribeToLivedata() {
        viewModel.sourcesLivedata.observe(
            viewLifecycleOwner
        ) {
            bindSourcesInTabLayout(it)
        }
        viewModel.showLoadingLayout.observe(viewLifecycleOwner) { show ->
            if (show)
                showLoadingLayout()
            else
                hideLoadingLayout()
        }
        viewModel.showErrorLayout.observe(viewLifecycleOwner) {
            showErrorLayout(it)
        }
    }

    fun changeNewsFragment(source: Source) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsFragment.getInstance(source))
            .commit()
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
        fun getInstance(category: Category): CategoryDetailsFragment {
            val fragment = CategoryDetailsFragment()
            fragment.category = category
            return fragment
        }
    }
}