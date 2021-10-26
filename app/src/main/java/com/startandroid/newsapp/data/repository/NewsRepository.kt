package com.startandroid.newsapp.data.repository

import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.model.StoriesNews
import io.reactivex.Single

interface NewsRepository {

    fun getMostPopular(): Single<PopularNews>

    fun getTopStories(): Single<StoriesNews>

    fun isNetConnected(): Boolean
}