package com.startandroid.newsapp.data

import com.startandroid.newsapp.data.entity.News
import com.startandroid.newsapp.data.entity.Stories
import io.reactivex.Single

interface NewsRepository {

    fun getMostPopular(): Single<News>

    fun getTopStories(): Single<Stories>
}