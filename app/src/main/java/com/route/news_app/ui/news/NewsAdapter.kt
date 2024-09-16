package com.route.news_app.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.news_app.data.api.model.newsResponse.News
import com.route.news_app.databinding.ItemNewsBinding

class NewsAdapter(var items: List<News?>?) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val viewBinding: ItemNewsBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(news: News?) {
            viewBinding.news = news
            viewBinding.invalidateAll()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items!![position]
        holder.bind(item)
        if (onItemClickListener != null) {
            holder.itemView.rootView.setOnClickListener {
                onItemClickListener?.onItemClick(item)
            }
        }

    }

    override fun getItemCount(): Int = items?.size ?: 0
    fun changeData(articles: List<News?>?) {
        items = articles
        notifyDataSetChanged()

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return 0
        return 1
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(news: News?) {
        }
    }
}