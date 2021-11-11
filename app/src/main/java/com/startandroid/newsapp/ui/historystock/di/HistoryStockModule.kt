package com.startandroid.newsapp.ui.historystock.di

import com.startandroid.newsapp.data.api.network.ApiRequest
import com.startandroid.newsapp.data.api.network.di.NetworkServiceModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [NetworkServiceModule::class])
class HistoryStockModule {
}