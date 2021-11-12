package com.startandroid.newsapp.data.api.network

import com.startandroid.newsapp.data.model.HistoryStock
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.model.StoriesNews
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("https://api.nytimes.com/svc/mostpopular/v2/shared/1.json")
    suspend fun getMostPopular(@Query("api-key") apiKey: String): PopularNews

    @GET("https://api.nytimes.com/svc/topstories/v2/home.json")
    suspend fun getTopStories(@Query("api-key") apiKey: String): StoriesNews

    @GET("WIKI/AAPL/data.json?api_key=xw3sqcPrQ42gQnuv4sJQ")
    suspend fun getHistoryStock(
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String
    ): HistoryStock
}