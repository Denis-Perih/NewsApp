package com.startandroid.newsapp.ui.main

import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.model.StoriesNewsItem

interface MainContract {

    fun openHomeFragment()

    fun openSignInFragment()

    fun noNetConnected()

    fun openPopularNewsMoreFragment(popularNewsItem: PopularNewsItem)

    fun openTopStoriesMoreFragment(storiesNewsItem: StoriesNewsItem)

}