package com.startandroid.newsapp.data.api.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.startandroid.newsapp.data.model.HistoryStock
import com.startandroid.newsapp.data.model.parser.HistoryStockParser
import com.startandroid.newsapp.utils.qualifier.GsonHistoryStockFactory
import com.startandroid.newsapp.utils.qualifier.GsonNewsFactory
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class GsonFactoryModule {

    @GsonNewsFactory
    @Provides
    fun gsonConverterNewsFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun gson(): Gson {
        return GsonBuilder().apply {
            registerTypeAdapter(HistoryStock::class.java, HistoryStockParser())
        }.create()
    }

    @GsonHistoryStockFactory
    @Provides
    fun gsonConverterHistoryFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
}