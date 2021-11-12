package com.startandroid.newsapp.ui.usingflow.di

import com.startandroid.newsapp.data.api.states.di.ConnectivityManagerModule
import com.startandroid.newsapp.data.api.states.di.LocationManagerModule
import com.startandroid.newsapp.ui.usingflow.view.UsingFlowFragment
import com.startandroid.newsapp.utils.scope.ApplicationScope
import dagger.Component
import kotlinx.coroutines.InternalCoroutinesApi

@ApplicationScope
@Component(modules = [ConnectivityManagerModule::class, LocationManagerModule::class])
interface UsingFlowComponent {

    @InternalCoroutinesApi
    fun injectUsingFlowFragment(usingFlowFragment: UsingFlowFragment)
}