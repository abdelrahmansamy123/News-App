package com.route.news_app.newsDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.news_app.databinding.ActivityNewsDetailsBinding
import com.route.news_app.newsResponse.News

class NewsDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsDetailsBinding

    private lateinit var news: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        news = ((intent.getSerializableExtra("news") as? News)!!)
        binding.news = news
    }
}