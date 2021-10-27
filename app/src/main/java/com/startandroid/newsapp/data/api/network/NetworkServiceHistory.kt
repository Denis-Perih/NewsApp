package com.startandroid.newsapp.data.api.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceHistory {

    private const val BASE_URL = "https://www.quandl.com/api/v3/datasets/"

    private val retrofit: Retrofit

    fun getJSONApi(): JSONNewsApi = retrofit.create(JSONNewsApi::class.java)

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}