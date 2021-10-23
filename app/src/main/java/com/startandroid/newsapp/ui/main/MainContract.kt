package com.startandroid.newsapp.ui.main

import com.startandroid.newsapp.data.entity.NewsItem
import com.startandroid.newsapp.data.entity.StoriesItem

interface MainContract {

    fun openHomeFragment()

    fun openSignInFragment()

    fun noNetConnected()

    fun openTab1MoreDetailsFragment(newsItem: NewsItem)

    fun openTab2MoreDetailsFragment(storiesItem: StoriesItem)

}