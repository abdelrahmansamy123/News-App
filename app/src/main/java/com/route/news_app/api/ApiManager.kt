package com.route.news_app.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object {
        var retrofit: Retrofit? = null

        @Synchronized
        fun getInstance(): Retrofit {
            if (retrofit == null) {
                val loggingInterceptor = HttpLoggingInterceptor{
                    Log.e("api",it)
                }

                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                val OkHttpClient = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .client(OkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        fun getApis(): WebServices {
            return getInstance().create(WebServices::class.java)
        }
    }

}