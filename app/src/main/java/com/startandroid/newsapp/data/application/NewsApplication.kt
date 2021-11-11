package com.startandroid.newsapp.data.application

import com.startandroid.newsapp.data.application.di.NewsAppApplicationComponent
import com.startandroid.newsapp.data.application.di.DaggerNewsAppApplicationComponent
import com.startandroid.newsapp.data.api.states.di.ApplicationContextModule
import android.app.Activity
import android.app.Application

class NewsApplication : Application() {
    var newsAppApplicationComponent: NewsAppApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        newsAppApplicationComponent = DaggerNewsAppApplicationComponent
            .builder()
            .applicationContextModule(ApplicationContextModule(this))
            .build()
    }

    companion object {
        operator fun get(activity: Activity): NewsApplication {
            return activity.application as NewsApplication
        }
    }
}