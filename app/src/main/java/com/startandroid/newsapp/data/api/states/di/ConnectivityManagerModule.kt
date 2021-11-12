package com.startandroid.newsapp.data.api.states.di

import android.content.Context
import android.net.ConnectivityManager
import com.startandroid.newsapp.utils.qualifier.ApplicationContext
import com.startandroid.newsapp.utils.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationContextModule::class])
class ConnectivityManagerModule {

    @ApplicationScope
    @Provides
    fun connectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}