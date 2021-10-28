package com.startandroid.newsapp.ui.home

import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.model.StoriesNewsItem

interface ItemMoreInterface {

    fun openItemMostPopular(popularNewsItem: PopularNewsItem)

    fun openItemTopStories(storiesNewsItem: StoriesNewsItem)
}