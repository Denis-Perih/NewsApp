package com.startandroid.newsapp.tab1

import android.app.Activity
import android.content.Context
import com.startandroid.newsapp.tab1.adapter.RecyclerViewAdapter

interface TabOneContract {

    interface View {

        fun noNetConnection()

        fun buildListNews()

        fun setAdapter(rvAdapter: RecyclerViewAdapter)
    }

    interface Presenter {

        fun startFragment(context: Context)
    }
}