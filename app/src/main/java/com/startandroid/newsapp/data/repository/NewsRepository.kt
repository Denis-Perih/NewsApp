package com.startandroid.newsapp.data.repository

import com.startandroid.newsapp.data.model.HistoryStock
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.model.StoriesNews
import io.reactivex.Single

interface NewsRepository {

    suspend fun getMostPopular(): PopularNews

    suspend fun getTopStories(): Single<StoriesNews>

    suspend fun isNetConnected(): Boolean

    suspend fun getHistoryStock(start_date: String, end_date: String): Single<HistoryStock>
}