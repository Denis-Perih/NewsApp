package com.startandroid.newsapp.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.startandroid.newsapp.tab1.TabOneFragment
import com.startandroid.newsapp.tab2.TabTwoFragment
import com.startandroid.newsapp.tab3.TabThreeFragment

class PageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {



    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return TabOneFragment()
            }
            1 -> {
                return TabTwoFragment()
            }
            2 -> {
                return TabThreeFragment()
            }
            else -> {
                return TabOneFragment()
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