package com.startandroid.newsapp.ui.tab2

import android.content.Context
import com.startandroid.newsapp.data.entity.StoriesItem
import com.startandroid.newsapp.ui.tab2.adapter.RecyclerViewAdapterTab2

interface TabTwoContract {

    interface View {

        fun buildListNews()

        fun noNetConnection()

        fun setAdapter(rvAdapterTab2: RecyclerViewAdapterTab2)

        fun openTab2FragmentMoreDetails(storiesItem: StoriesItem)
    }

    interface Presenter {

        fun startFragment(context: Context)
    }
}