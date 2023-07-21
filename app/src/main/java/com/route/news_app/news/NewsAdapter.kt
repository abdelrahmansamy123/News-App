package com.route.news_app.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.route.news_app.R
import com.route.news_app.databinding.ItemNewsBinding
import com.route.news_app.newsResponse.News

class NewsAdapter(var items: List<News?>?) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder (val viewBinding:ItemNewsBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(news: News?){
            viewBinding.news = news
            viewBinding.invalidateAll()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.bind(item)

    }

    override fun getItemCount(): Int = items?.size?:0
    fun changeData(articles: List<News?>?) {
        items = articles
        notifyDataSetChanged()

    }
}