package com.startandroid.newsapp.data.api.states.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.startandroid.newsapp.utils.qualifier.ApplicationContext
import com.startandroid.newsapp.utils.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationContextModule::class])
class ConnectivityManagerModule {

    @RequiresApi(Build.VERSION_CODES.M)
    @Provides
    fun checkInternet(connectivityManager: ConnectivityManager): Boolean {
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                return true
            }
        }
        return false
    }

    @ApplicationScope
    @Provides
    fun connectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}