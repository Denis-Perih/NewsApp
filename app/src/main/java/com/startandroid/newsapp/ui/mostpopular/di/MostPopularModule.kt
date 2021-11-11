package com.startandroid.newsapp.ui.mostpopular.di

import com.startandroid.newsapp.data.api.network.di.NetworkServiceModule
import dagger.Module

@Module(includes = [NetworkServiceModule::class])
class MostPopularModule {
}