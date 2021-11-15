package com.startandroid.newsapp.data.application

import android.app.Application
import com.startandroid.newsapp.data.api.states.di.ApplicationContextModule

class NewsApplication : Application() {

    lateinit var appComponent: NewsAppApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerNewsAppApplicationComponent
            .builder()
            .applicationContextModule(ApplicationContextModule(this))
            .build()
    }

    companion object {
        lateinit var INSTANCE: NewsApplication
    }
}