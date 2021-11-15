package com.startandroid.newsapp.data.application

import android.content.Context
import com.startandroid.newsapp.data.api.network.di.NetworkServiceModule
import com.startandroid.newsapp.data.api.states.di.ApplicationContextModule
import com.startandroid.newsapp.data.api.states.di.ConnectivityManagerModule
import com.startandroid.newsapp.data.api.states.di.LocationManagerModule
import com.startandroid.newsapp.data.application.di.CiceroneNavigationModule
import com.startandroid.newsapp.ui.historystock.view.HistoryStockFragment
import com.startandroid.newsapp.ui.home.HomeFragment
import com.startandroid.newsapp.ui.main.MainActivity
import com.startandroid.newsapp.ui.more.MoreItemFragment
import com.startandroid.newsapp.ui.mostpopular.view.MostPopularFragment
import com.startandroid.newsapp.ui.signin.view.SignInFragment
import com.startandroid.newsapp.ui.splash.SplashFragment
import com.startandroid.newsapp.ui.topstories.view.TopStoriesFragment
import com.startandroid.newsapp.ui.usingflow.view.UsingFlowFragment
import com.startandroid.newsapp.utils.qualifier.ApplicationContext
import com.startandroid.newsapp.utils.scope.ApplicationScope
import dagger.Component
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ApplicationScope
@Component(
    modules = [
        ApplicationContextModule::class,
        ConnectivityManagerModule::class,
        LocationManagerModule::class,
        CiceroneNavigationModule::class,
        NetworkServiceModule::class
    ]
)
interface NewsAppApplicationComponent {

    @ApplicationContext
    fun getApplicationContext(): Context

    @InternalCoroutinesApi
    fun inject(activity: MainActivity)

    @InternalCoroutinesApi
    fun inject(fragment: SplashFragment)

    @InternalCoroutinesApi
    fun inject(fragment: SignInFragment)

    // don't use
//    fun inject(fragment: HomeFragment)

    @InternalCoroutinesApi
    fun inject(fragment: MostPopularFragment)

    @InternalCoroutinesApi
    fun inject(fragment: TopStoriesFragment)

    @ObsoleteCoroutinesApi
    fun inject(fragment: HistoryStockFragment)

    @InternalCoroutinesApi
    fun inject(fragment: UsingFlowFragment)

    @InternalCoroutinesApi
    fun inject(fragment: MoreItemFragment)
}