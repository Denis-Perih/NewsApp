package com.startandroid.newsapp.data.repository.repositorynews

import com.startandroid.newsapp.data.model.HistoryStock
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.model.StoriesNews

interface NewsRepository {

    suspend fun getMostPopular(): PopularNews

    suspend fun getTopStories(): StoriesNews

    suspend fun isNetConnected(): Boolean

    suspend fun getHistoryStock(start_date: String, end_date: String): HistoryStock
}