package com.startandroid.newsapp.ui.topstories.di

import com.startandroid.newsapp.data.api.network.di.NetworkServiceModule
import com.startandroid.newsapp.ui.topstories.view.TopStoriesFragment
import com.startandroid.newsapp.utils.scope.NewsFragmentScope
import dagger.Component

@NewsFragmentScope
@Component(modules = [NetworkServiceModule::class])
interface TopStoriesComponent {

    fun injectTopStoriesFragment(topStoriesFragment: TopStoriesFragment)
}