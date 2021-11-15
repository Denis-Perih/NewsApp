package com.startandroid.newsapp.data.application.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.startandroid.newsapp.utils.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class CiceroneNavigationModule {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @ApplicationScope
    fun router(): Router {
        return cicerone.router
    }

    @Provides
    @ApplicationScope
    fun navigationHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}