package com.startandroid.newsapp.ui.usingflow.di

import com.startandroid.newsapp.data.api.states.di.ConnectivityManagerModule
import com.startandroid.newsapp.data.api.states.di.LocationManagerModule
import dagger.Module

@Module(includes = [ConnectivityManagerModule::class, LocationManagerModule::class])
class UsingFlowModule {
}