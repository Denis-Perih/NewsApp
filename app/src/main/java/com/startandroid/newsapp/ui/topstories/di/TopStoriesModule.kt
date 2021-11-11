package com.startandroid.newsapp.ui.topstories.di

import com.startandroid.newsapp.data.api.network.di.NetworkServiceModule
import dagger.Module

@Module(includes = [NetworkServiceModule::class])
class TopStoriesModule {

}