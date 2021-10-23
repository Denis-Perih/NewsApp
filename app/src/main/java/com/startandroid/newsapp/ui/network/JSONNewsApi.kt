package com.startandroid.newsapp.ui.network

import retrofit2.http.GET
import com.startandroid.newsapp.data.entity.News
import com.startandroid.newsapp.data.entity.Stories
import io.reactivex.Single
import retrofit2.http.Query

interface JSONNewsApi {
    @GET("https://api.nytimes.com/svc/mostpopular/v2/shared/1.json")
    fun getNews(@Query("api-key") apiKey: String): Single<News>

    @GET("https://api.nytimes.com/svc/topstories/v2/home.json")
    fun getTopStories(@Query("api-key") apiKey: String): Single<Stories>
}