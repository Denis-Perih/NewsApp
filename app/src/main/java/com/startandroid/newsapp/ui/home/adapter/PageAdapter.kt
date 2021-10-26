package com.startandroid.newsapp.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.startandroid.newsapp.ui.mostpopular.view.MostPopularFragment
import com.startandroid.newsapp.ui.topstories.view.TopStoriesFragment
import com.startandroid.newsapp.ui.historystock.TabThreeFragment

class PageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {



    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return MostPopularFragment()
            }
            1 -> {
                return TopStoriesFragment()
            }
            2 -> {
                return TabThreeFragment()
            }
            else -> {
                return MostPopularFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Tab 1"
            }
            1 -> {
                return "Tab 2"
            }
            2 -> {
                return "Tab 3"
            }
        }
        return super.getPageTitle(position)
    }
}