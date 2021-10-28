package com.startandroid.newsapp.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.startandroid.newsapp.ui.mostpopular.view.MostPopularFragment
import com.startandroid.newsapp.ui.topstories.view.TopStoriesFragment
import com.startandroid.newsapp.ui.historystock.view.HistoryStockFragment
import com.startandroid.newsapp.ui.home.HomeFragment
import com.startandroid.newsapp.ui.home.ItemMoreInterface

class PageAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                MostPopularFragment()
            }
            1 -> {
                TopStoriesFragment()
            }
            2 -> {
                HistoryStockFragment()
            }
            else -> {
                MostPopularFragment()
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