package com.startandroid.newsapp.ui.tab1

import android.content.Context
import com.startandroid.newsapp.data.entity.NewsItem
import com.startandroid.newsapp.ui.tab1.adapter.RecyclerViewAdapterTab1

interface TabOneContract {

    interface View {

        fun noNetConnection()

        fun buildListNews()

        fun setAdapter(rvAdapterTab1: RecyclerViewAdapterTab1)

        fun openTab1FragmentMoreDetails(newsItem: NewsItem)
    }

    interface Presenter {

        fun startFragment(context: Context)
    }
}