package com.startandroid.newsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.startandroid.newsapp.R
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.ui.home.adapter.PageAdapter
import com.startandroid.newsapp.ui.more.MoreMostPopular
import com.startandroid.newsapp.ui.more.MoreTopStories
import com.startandroid.newsapp.ui.mostpopular.view.MostPopularFragment
import com.startandroid.newsapp.utils.IOnBackPressed


class HomeFragment : Fragment(), IOnBackPressed, MoreMostPopular, MoreTopStories {

    lateinit var tlTabsFragments: TabLayout
    lateinit var vpPagerFragments: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeView: View = inflater.inflate(R.layout.fr_home_screen, container, false)

        vpPagerFragments = homeView.findViewById(R.id.vpPagerFragments)
        vpPagerFragments.adapter = PageAdapter(requireActivity().supportFragmentManager)

        tlTabsFragments = homeView.findViewById(R.id.tlTabsFragments)
        tlTabsFragments.setupWithViewPager(vpPagerFragments)

        return homeView
    }

    override fun onBackPressed(): Boolean {
        val manager: FragmentManager = (context as AppCompatActivity)
            .supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            requireActivity().finish()
        }
        return true
    }

    override fun openMoreMostPopular(popularNewsItem: PopularNewsItem) {
        TODO("Not yet implemented")
    }

    override fun openMoreTopStories(storiesNewsItem: StoriesNewsItem) {
        TODO("Not yet implemented")
    }
}