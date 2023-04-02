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

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = items?.get(position)
        holder.viewBinding.author.text =  items?.author
        holder.viewBinding.title.text =  items?.title
        holder.viewBinding.time.text =  items?.publishedAt
        Glide.with(holder.itemView)
            .load(items?.urlToImage)
            .placeholder(R.drawable.ic_image)
            .into(holder.viewBinding.image)
    }

    override fun getItemCount(): Int = items?.size?:0
    fun changeData(articles: List<News?>?) {
        items = articles
        notifyDataSetChanged()

    }
}