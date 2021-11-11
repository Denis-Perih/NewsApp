package com.startandroid.newsapp.data.application.di

import android.content.Context
import com.startandroid.newsapp.data.api.states.di.ApplicationContextModule
import com.startandroid.newsapp.data.api.states.di.ConnectivityManagerModule
import com.startandroid.newsapp.utils.qualifier.ApplicationContext
import com.startandroid.newsapp.utils.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationContextModule::class, ConnectivityManagerModule::class])
interface NewsAppApplicationComponent {

    @ApplicationContext
    fun getApplicationContext(): Context
}