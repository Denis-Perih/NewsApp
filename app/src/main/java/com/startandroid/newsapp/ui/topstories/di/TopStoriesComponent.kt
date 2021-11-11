package com.startandroid.newsapp.ui.topstories.di

import com.startandroid.newsapp.ui.topstories.view.TopStoriesFragment
import com.startandroid.newsapp.utils.scope.NewsFragmentScope
import dagger.Component

@NewsFragmentScope
@Component(modules = [TopStoriesModule::class])
interface TopStoriesComponent {

    fun injectTopStoriesFragment(topStoriesFragment: TopStoriesFragment)
}