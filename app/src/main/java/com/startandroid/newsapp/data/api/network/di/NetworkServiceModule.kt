package com.startandroid.newsapp.data.api.network.di

import com.startandroid.newsapp.data.api.network.ApiRequest
import com.startandroid.newsapp.utils.qualifier.*
import com.startandroid.newsapp.utils.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [OkHttpModule::class, GsonFactoryModule::class])
class NetworkServiceModule {

    @HistoryStockApiRequest
    @Provides
    fun historyApiRequest(@RetrofitHistoryStock retrofitHistory: Retrofit): ApiRequest {
        return retrofitHistory.create(ApiRequest::class.java)
    }

    @ApplicationScope
    @RetrofitHistoryStock
    @Provides
    fun retrofitHistory(
        httpClient: OkHttpClient,
        @GsonHistoryStockFactory gsonConverterHistoryFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.quandl.com/api/v3/datasets/")
            .addConverterFactory(gsonConverterHistoryFactory)
            .client(httpClient)
            .build()
    }

    @NewsApiRequest
    @Provides
    fun newsApiRequest(@RetrofitNews retrofitNews: Retrofit): ApiRequest {
        return retrofitNews.create(ApiRequest::class.java)
    }

    @ApplicationScope
    @RetrofitNews
    @Provides
    fun retrofitNews(
        httpClient: OkHttpClient,
        @GsonNewsFactory gsonConverterNewsFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
            .addConverterFactory(gsonConverterNewsFactory)
            .client(httpClient)
            .build()
    }
}