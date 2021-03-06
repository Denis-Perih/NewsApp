package com.startandroid.newsapp.ui.network

import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object NetworkServiceTabOneTwo {

    private const val BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"

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