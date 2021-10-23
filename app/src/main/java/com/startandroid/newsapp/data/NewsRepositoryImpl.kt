package com.startandroid.newsapp.data

import com.startandroid.newsapp.ui.network.NetworkServiceTabOneTwo
import com.startandroid.newsapp.data.entity.News
import com.startandroid.newsapp.data.entity.Stories
import io.reactivex.Single

class NewsRepositoryImpl : NewsRepository {

    private val apiKey = "AX61V3XwAl1cpB4ZM04aSr5Ae7Ax4SGF"

    override fun getMostPopular(): Single<News> {

        return NetworkServiceTabOneTwo
            .getJSONApi()
            .getNews(apiKey)
    }

    override fun getTopStories(): Single<Stories> {
        return  NetworkServiceTabOneTwo
            .getJSONApi()
            .getTopStories(apiKey)
    }
}