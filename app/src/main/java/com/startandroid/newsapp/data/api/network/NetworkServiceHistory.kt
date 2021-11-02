package com.startandroid.newsapp.data.api.network

import com.google.gson.GsonBuilder
import com.startandroid.newsapp.data.model.HistoryStock
import com.startandroid.newsapp.data.model.parser.HistoryStockParser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceHistory {

    private const val BASE_URL = "https://www.quandl.com/api/v3/datasets/"

    private val retrofit: Retrofit

    fun getJSONApi(): JSONNewsApi = retrofit.create(JSONNewsApi::class.java)

    init {
        val gson = GsonBuilder().apply {
            registerTypeAdapter(HistoryStock::class.java, HistoryStockParser())
        }.create()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client.build())
            .build()
    }
}