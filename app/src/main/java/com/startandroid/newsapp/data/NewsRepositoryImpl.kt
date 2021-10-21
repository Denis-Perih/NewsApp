package com.startandroid.newsapp.data

import com.startandroid.newsapp.network.NetworkServiceTab1
import com.startandroid.newsapp.data.entity.News
import io.reactivex.Single

class NewsRepositoryImpl : NewsRepository {
    override fun getMostPopular(): Single<News> {
        val apiKey = "AX61V3XwAl1cpB4ZM04aSr5Ae7Ax4SGF"
        return NetworkServiceTab1
            .getJSONApi()
            .getNews(apiKey)
    }

}