package com.startandroid.newsapp.data

import com.startandroid.newsapp.data.entity.News
import io.reactivex.Single

interface NewsRepository {
    fun getMostPopular(): Single<News>

}