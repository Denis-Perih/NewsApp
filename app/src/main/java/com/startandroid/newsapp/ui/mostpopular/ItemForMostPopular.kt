package com.startandroid.newsapp.ui.mostpopular

import com.startandroid.newsapp.data.model.PopularNewsItem

interface ItemForMostPopular {

    fun openItemMoreDetails(popularNewsItem: PopularNewsItem)
}