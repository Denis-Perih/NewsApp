package com.startandroid.newsapp.data.api.network

import com.startandroid.newsapp.data.model.HistoryStock
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.model.StoriesNews
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface JSONNewsApi {
    @GET("https://api.nytimes.com/svc/mostpopular/v2/shared/1.json")
    fun getMostPopular(@Query("api-key") apiKey: String): Deferred<PopularNews>

    @GET("https://api.nytimes.com/svc/topstories/v2/home.json")
    fun getTopStories(@Query("api-key") apiKey: String): Deferred<StoriesNews>

    @GET("WIKI/AAPL/data.json?api_key=xw3sqcPrQ42gQnuv4sJQ")
    fun getHistoryStock(
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String
    ): Deferred<HistoryStock>
}