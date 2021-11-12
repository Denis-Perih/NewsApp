package com.startandroid.newsapp.data.api.states.di

import android.content.Context
import com.startandroid.newsapp.utils.qualifier.ApplicationContext
import com.startandroid.newsapp.utils.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule(private val context: Context) {

    @ApplicationScope
    @ApplicationContext
    @Provides
    fun applicationContext(): Context {
        return context
    }
}