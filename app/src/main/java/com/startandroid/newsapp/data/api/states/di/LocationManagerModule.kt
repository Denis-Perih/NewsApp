package com.startandroid.newsapp.data.api.states.di

import android.content.Context
import android.location.LocationManager
import com.startandroid.newsapp.utils.qualifier.ApplicationContext
import com.startandroid.newsapp.utils.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class LocationManagerModule {

    //maybe need add <private var locationManager: LocationManager? = null>

    @ApplicationScope
    @Provides
    fun locationManager(@ApplicationContext context: Context): LocationManager {
        return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
}